package com.glowman.spaceunit.game.strategy;


import android.util.Log;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.game.animation.BlowAnimation;
import com.glowman.spaceunit.game.core.AnimatedSprite;
import com.glowman.spaceunit.game.core.TouchEvent;
import com.glowman.spaceunit.game.mapObject.Bullet;
import com.glowman.spaceunit.game.mapObject.enemy.Enemy;
import com.glowman.spaceunit.game.mapObject.Ship;

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

	protected int _shootingTouch = -1;
	protected int _movingTouch = -1;

	GameStrategy(Ship ship)
	{
		_heroShip = ship;
		_enemies = null;
		_deadEnemies = null;
	}

	public ArrayList<Enemy> getEnemies() { return _enemies; }
	public ArrayList<Bullet> getBullets() { return null; }
	public ArrayList<Enemy> getDeadEnemies() { return _deadEnemies; }
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

	protected void createEnemy()
	{
		if (_enemies == null)
		{
			_enemies = new ArrayList<Enemy>();
		}
		Sprite image = new Sprite(Assets.soImages[Math.round((float)Math.random())]);
		Enemy enemy = new Enemy(image, true);
		enemy.setRandomBorderPosition();
		enemy.setRandomGeneralSpeed();
		_enemies.add(enemy);
	}

	public void explodeEnemy(Enemy enemy) {
		enemy.getImage().setScale(.1f);
		enemy.stop();
		enemy.setGeneralSpeed(0);
		_enemies.remove(enemy);
		if (_deadEnemies == null) { _deadEnemies = new ArrayList<Enemy>(); }
		_deadEnemies.add(enemy);

		AnimatedSprite animation = new BlowAnimation(enemy);
		if (_animations == null) { _animations = new ArrayList<AnimatedSprite>(); }
		_animations.add(animation);
	}

	public abstract void touchUp(TouchEvent touch);
	public abstract void touchDown(TouchEvent touch);
	public abstract void touchMove(TouchEvent touch);

}
