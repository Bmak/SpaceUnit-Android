package com.glowman.spaceunit.game.strategy;


import com.glowman.android.framework.Graphics;
import com.glowman.spaceunit.game.mapObject.Enemy;
import com.glowman.spaceunit.game.mapObject.Ship;

import java.util.ArrayList;

/**
 *
 */
public abstract class GameStrategy {
	protected ArrayList<Enemy> _enemies;
	protected Ship _heroShip;
	protected Graphics _graphics;

	GameStrategy(Graphics graphics, Ship ship)
	{
		_graphics = graphics;
		_heroShip = ship;
		_enemies = null;
	}

	public ArrayList<Enemy> getEnemies() { return _enemies; }

	public abstract void update();

	public abstract void createEnemy();

	public void explodeEnemy(Enemy enemy) {
		enemy.getImage().setScale(10);
		_enemies.remove(enemy);
	}
}
