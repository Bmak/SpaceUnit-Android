package com.glowman.spaceunit.game.strategy;


import android.util.Log;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.game.core.TouchEvent;
import com.glowman.spaceunit.game.mapObject.Enemy;
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
	protected Ship _heroShip;
	protected Vector2 _screenSize;

	protected int _shootingTouch = -1;
	protected int _movingTouch = -1;

	GameStrategy(Ship ship, Vector2 screenSize)
	{
		_heroShip = ship;
		_enemies = null;
		_deadEnemies = null;
		_screenSize = screenSize;
	}

	public ArrayList<Enemy> getEnemies() { return _enemies; }

	public ArrayList<Enemy> getDeadEnemies() { return _deadEnemies; }

	public abstract void tick(float delta);

	protected void createEnemy()
	{
		Log.d("hz", "creating enemy");
		if (_enemies == null)
		{
			_enemies = new ArrayList<Enemy>();
		}
		Sprite image = new Sprite(Assets.soImages[Math.round((float)Math.random())]);
		Enemy enemy = new Enemy(image, _screenSize, true);
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
	}

	public abstract void touchUp(TouchEvent touch);
	public abstract void touchDown(TouchEvent touch);
	public abstract void touchMove(TouchEvent touch);
}
