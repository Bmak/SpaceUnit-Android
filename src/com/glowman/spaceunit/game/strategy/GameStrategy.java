package com.glowman.spaceunit.game.strategy;


import com.glowman.spaceunit.game.animation.BlowAnimation;
import com.glowman.spaceunit.core.AnimatedSprite;
import com.glowman.spaceunit.core.TouchEvent;
import com.glowman.spaceunit.game.mapObject.Bullet;
import com.glowman.spaceunit.game.mapObject.enemy.AEnemy;
import com.glowman.spaceunit.game.mapObject.Ship;
import com.glowman.spaceunit.game.mapObject.enemy.EnemyFactory;

import java.util.ArrayList;

/**
 *
 */
public abstract class GameStrategy {
	public static final int SHOOT_GAME = 0;
	public static final int RUN_GAME = 1;

	protected ArrayList<AEnemy> _enemies;
	protected ArrayList<AEnemy> _deadEnemies;
	protected ArrayList<AnimatedSprite> _animations;
	protected Ship _heroShip;

	protected int _shootingTouch = -1;
	protected int _movingTouch = -1;

	GameStrategy(Ship ship)
	{
		_heroShip = ship;
		_enemies = null;
		_deadEnemies = null;
	}

	public ArrayList<AEnemy> getEnemies() { return _enemies; }
	public ArrayList<Bullet> getBullets() { return null; }
	public ArrayList<AEnemy> getDeadEnemies() { return _deadEnemies; }
	public ArrayList<AnimatedSprite> getAnimations() { return _animations; }

	public void tick(float delta) {

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

	protected AEnemy createEnemy()
	{
		if (_enemies == null)
		{
			_enemies = new ArrayList<AEnemy>();
		}
		AEnemy enemy = EnemyFactory.createEnemy();
		_enemies.add(enemy);
		return enemy;
	}

	public void explodeEnemy(AEnemy enemy) {
		enemy.getImage().setScale(.1f);
		enemy.stop();
		enemy.setGeneralSpeed(0);
		_enemies.remove(enemy);
		if (_deadEnemies == null) { _deadEnemies = new ArrayList<AEnemy>(); }
		_deadEnemies.add(enemy);

		AnimatedSprite animation = new BlowAnimation(enemy);
		if (_animations == null) { _animations = new ArrayList<AnimatedSprite>(); }
		_animations.add(animation);
	}

	public abstract void touchUp(TouchEvent touch);
	public abstract void touchDown(TouchEvent touch);
	public abstract void touchMove(TouchEvent touch);

}
