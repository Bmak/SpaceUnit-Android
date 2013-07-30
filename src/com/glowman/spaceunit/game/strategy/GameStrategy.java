package com.glowman.spaceunit.game.strategy;


import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.game.balance.RespawnFrequencyCollector;
import com.glowman.spaceunit.game.animation.BlowAnimation;
import com.glowman.spaceunit.core.AnimatedSprite;
import com.glowman.spaceunit.core.TouchEvent;
import com.glowman.spaceunit.game.mapObject.Bullet;
import com.glowman.spaceunit.game.mapObject.SpaceObject;
import com.glowman.spaceunit.game.mapObject.enemy.Enemy;
import com.glowman.spaceunit.game.mapObject.Ship;
import com.glowman.spaceunit.game.mapObject.enemy.EnemyFactory;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.EnemyBehaviourNameENUM;

import java.util.ArrayList;

/**
 *
 */
public abstract class GameStrategy {
	public static final int SHOOT_GAME = 0;
	public static final int RUN_GAME = 1;

	protected ArrayList<Enemy> _enemies;
	protected ArrayList<Enemy> _deadEnemies;
	protected ArrayList<AnimatedSprite> _animations;
	protected Ship _heroShip;

	protected String[] _availableEnemyTypes;

	protected int _shootingTouch = -1;
	protected int _movingTouch = -1;

	private boolean _gameOver;

	GameStrategy(Ship ship)
	{
		_heroShip = ship;
		_enemies = null;
		_deadEnemies = null;
	}

	public void startGame() {
		_gameOver = false;
	}

	public boolean isGameOver() { return _gameOver; }

	public ArrayList<Enemy> getEnemies() { return _enemies; }
	public ArrayList<Bullet> getBullets() { return null; }
	public ArrayList<Enemy> getDeadEnemies() { return _deadEnemies; }
	public ArrayList<AnimatedSprite> getAnimations() { return _animations; }

	public void tick(float delta) {
		if (!_gameOver) this.createEnemies();

		this.tickAnimations(delta);
		this.tickEnemies(delta);
	}

	private void tickAnimations(float delta) {
		if (_animations != null)
		{
			ArrayList<AnimatedSprite> animationsForRemove = new ArrayList<AnimatedSprite>();
			for (AnimatedSprite animation : _animations)
			{
				if (animation.isAnimationFinished())
				{
					animationsForRemove.add(animation);
				} else
				{
					animation.tick(delta);
				}
			}
			for (AnimatedSprite animation : animationsForRemove)
			{
				_animations.remove(animation);
			}
			animationsForRemove.clear();
		}
	}

	private void tickEnemies(float delta) {
		if (_enemies != null)
		{
			for (Enemy enemy : _enemies)
			{
				enemy.tick(delta);
			}
		}
	}

	private void createEnemies()
	{
		if (_availableEnemyTypes == null || _availableEnemyTypes.length == 0) {
			return;
		}
		if (_enemies == null)
		{
			_enemies = new ArrayList<Enemy>();
		}
		String enemyType;
		for (int i = 0; i < _availableEnemyTypes.length; ++i) {
			enemyType = _availableEnemyTypes[i];
			if (Math.random() <
					RespawnFrequencyCollector.getFrequency(enemyType, GameStrategy.SHOOT_GAME)) {
				Enemy enemy = EnemyFactory.createEnemy(enemyType);
				_enemies.add(enemy);
				this.setEnemyParams(enemy);
			}
		}
	}

	protected void setEnemyParams(Enemy enemy) {
		//need to override
	}

	protected void gameOver() {
		_gameOver = true;
		if (_enemies != null) {
			for (Enemy enemy : _enemies) {
				enemy.removeAllBehaviours();
			}
		}
	}

	protected boolean checkObjectsHit(SpaceObject object1, SpaceObject object2) {
		float distance;
		float radius1, radius2;
		Vector2 position1, position2;
		radius1 = object1.getHeight()/2;
		radius2 = object2.getHeight()/2;
		position1 = object1.getCenterPosition();
		position2 = object2.getCenterPosition();

		distance = position1.dst(position2);

		return distance < radius1 + radius2;
	}

	protected void explodeEnemy(Enemy enemy) {
		enemy.stop();
		enemy.setGeneralSpeed(0);
		_enemies.remove(enemy);
		this.blow(enemy.getCenterPosition().x, enemy.getCenterPosition().y, enemy.getWidth());
	}

	protected void explodeHero() {
		_heroShip.stop();
		_heroShip.setGeneralSpeed(0);
		this.blow(_heroShip.getCenterPosition().x, _heroShip.getCenterPosition().y, _heroShip.getWidth());
		_heroShip.setScale(.1f);
	}

	private void blow(float blowX, float blowY, float radius) {
		AnimatedSprite animation = new BlowAnimation(blowX, blowY, radius);
		if (_animations == null) { _animations = new ArrayList<AnimatedSprite>(); }
		_animations.add(animation);
	}

	public abstract void touchUp(TouchEvent touch);
	public abstract void touchDown(TouchEvent touch);
	public abstract void touchMove(TouchEvent touch);

}
