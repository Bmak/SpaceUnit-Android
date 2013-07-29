package com.glowman.spaceunit.game.strategy;

import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.core.TouchEvent;
import com.glowman.spaceunit.game.balance.EnemySetCollector;
import com.glowman.spaceunit.game.balance.SpeedCollector;
import com.glowman.spaceunit.game.mapObject.enemy.Enemy;
import com.glowman.spaceunit.game.mapObject.Ship;
import com.glowman.spaceunit.game.mapObject.enemy.EnemyFactory;

import java.lang.Math;
import java.util.ArrayList;

/**
 *
 */
public class GameRunStrategy extends GameStrategy {

	public GameRunStrategy(Ship ship)
	{
		super(ship);
		EnemyFactory.init(GameStrategy.RUN_GAME, _heroShip);
		_availableEnemyTypes = EnemySetCollector.getEnemySet(GameStrategy.RUN_GAME);
	}

	@Override
	public void tick(float delta)
	{
		super.tick(delta);
		super._heroShip.tick(delta);

		if (_enemies != null)
		{
			this.checkEnemyHits();
		}
	}

	@Override
	public void touchDown(TouchEvent touch) {
		_heroShip.setTargetPosition(new Vector2(touch.x, touch.y));
		_heroShip.setMoving(true);
	}

	@Override
	public void touchUp(TouchEvent touch) {

		_heroShip.setMoving(false);
	}

	@Override
	public void touchMove(TouchEvent touch) {

		_heroShip.setTargetPosition(new Vector2(touch.x, touch.y));
	}

	@Override
	protected void setEnemyParams(Enemy enemy) {
		enemy.setRandomBorderPosition();
		enemy.setRotationSpeed(5 * ((float)Math.random() * 2 - 1)); //TODO kick it out
		enemy.setGeneralSpeed(SpeedCollector.getEnemySpeed(enemy.getEnemyType(), GameStrategy.RUN_GAME));
		enemy.setTarget(_heroShip);
	}


	private void checkEnemyHits()
	{
		ArrayList<Enemy> enemiesForExplosion = new ArrayList<Enemy>();
		float distance;
		float radius1, radius2;
		Vector2 position1, position2;
		for (int i = 0; i < (_enemies.size() - 1); ++i) {
			for (int j = i + 1; j < _enemies.size(); ++j) {
				radius1 = _enemies.get(i).getHeight()/2;
				radius2 = _enemies.get(j).getHeight()/2;
				position1 = _enemies.get(i).getCenterPosition();
				position2 = _enemies.get(j).getCenterPosition();

				distance = position1.dst(position2);
				if (distance < radius1 + radius2)
				{
					enemiesForExplosion.add(_enemies.get(i));
					enemiesForExplosion.add(_enemies.get(j));
				}
			}
		}

		for (Enemy enemy : enemiesForExplosion)
		{
			super.explodeEnemy(enemy);
		}
	}

}
