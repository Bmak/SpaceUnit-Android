package com.glowman.spaceunit.game.strategy;


import com.badlogic.gdx.Input;
import com.glowman.spaceunit.game.mapObject.Enemy;
import com.glowman.spaceunit.game.mapObject.Ship;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class GameStrategy {
//	protected ArrayList<Enemy> _enemies;
//	protected ArrayList<Enemy> _deadEnemies;
//	protected Ship _heroShip;
//	protected Graphics _graphics;
//
//	GameStrategy(Graphics graphics, Ship ship)
//	{
//		_graphics = graphics;
//		_heroShip = ship;
//		_enemies = null;
//		_deadEnemies = null;
//	}
//
//	public ArrayList<Enemy> getEnemies() { return _enemies; }
//
//	public ArrayList<Enemy> getDeadEnemies() { return _deadEnemies; }
//
//	public abstract void update();
//
//	public abstract void createEnemy();
//
//	public void explodeEnemy(Enemy enemy) {
//		enemy.getImage().setScale(10);
//		enemy.setSpeedX(0);
//		enemy.setSpeedY(0);
//		enemy.setGeneralSpeed(0);
//		_enemies.remove(enemy);
//		if (_deadEnemies == null) { _deadEnemies = new ArrayList<Enemy>(); }
//		_deadEnemies.add(enemy);
//	}
//
//	public abstract void touchesBegan(List<Input.TouchEvent> touches);
//	public abstract void touchesEnded(List<Input.TouchEvent> touches);
//	public abstract void touchesMoved(List<Input.TouchEvent> touches);
}
