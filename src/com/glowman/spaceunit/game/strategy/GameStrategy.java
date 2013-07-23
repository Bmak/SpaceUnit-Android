package com.glowman.spaceunit.game.strategy;


import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.game.core.TouchEvent;
import com.glowman.spaceunit.game.mapObject.Enemy;
import com.glowman.spaceunit.game.mapObject.Ship;

import java.util.ArrayList;

/**
 *
 */
public abstract class GameStrategy {
	protected ArrayList<Enemy> _enemies;
	protected ArrayList<Enemy> _deadEnemies;
	protected Ship _heroShip;
	protected Vector2 _screenSize;

	GameStrategy(Ship ship, Vector2 screenSize)
	{
		_heroShip = ship;
		_enemies = null;
		_deadEnemies = null;
		_screenSize = screenSize;
	}

	public ArrayList<Enemy> getEnemies() { return _enemies; }

	public ArrayList<Enemy> getDeadEnemies() { return _deadEnemies; }

	public abstract void update();

	public abstract void createEnemy();

	public void explodeEnemy(Enemy enemy) {
		enemy.getImage().setScale(10);
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
