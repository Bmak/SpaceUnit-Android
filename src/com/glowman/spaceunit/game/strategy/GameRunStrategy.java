package com.glowman.spaceunit.game.strategy;

import android.util.Log;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.game.core.TouchEvent;
import com.glowman.spaceunit.game.mapObject.Enemy;
import com.glowman.spaceunit.game.mapObject.Ship;

import java.lang.Math;
import java.util.ArrayList;

/**
 *
 */
public class GameRunStrategy extends GameStrategy {

	public GameRunStrategy(Ship ship, Vector2 screenSize)
	{
		super(ship, screenSize);
	}

	@Override
	public void tick(float delta)
	{
		super._heroShip.tick(delta);

		//TODO set game balance here
		if (Math.random() < .03) { this.createEnemy(); }
		if (_enemies != null)
		{
			for (Enemy enemy : _enemies)
			{
				enemy.moveTo(_heroShip.getPosition().x, _heroShip.getPosition().y);
				enemy.tick(delta);
			}
		}
		if (_enemies != null)
		{
			this.checkEnemyHits();
		}
	}

	@Override
	public void touchDown(TouchEvent touch) {
		Log.d("hz", "touch down!");
		_heroShip.setTargetPosition(new Vector2(touch.x, touch.y));
		_heroShip.setMoving(true);
	}

	@Override
	public void touchUp(TouchEvent touch) {
		Log.d("hz", "touch up!");

		_heroShip.setMoving(false);
	}

	@Override
	public void touchMove(TouchEvent touch) {
		Log.d("hz", "touch move!");

		_heroShip.setTargetPosition(new Vector2(touch.x, touch.y));
	}


	private void checkEnemyHits()
	{
		ArrayList<Enemy> enemiesForExplosion = new ArrayList<Enemy>();
		float distance;
		float radius1, radius2;
		Vector2 position1, position2;
		for (int i = 0; i < (_enemies.size() - 1); ++i) {
			for (int j = i + 1; j < _enemies.size(); ++j) {
				radius1 = _enemies.get(i).getImage().getHeight()/2;// *
						//(_enemies.get(i).getImage().getScale()/100);
				radius2 = _enemies.get(j).getImage().getHeight()/2;// *
						//(_enemies.get(j).getImage().getScale()/100);
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
