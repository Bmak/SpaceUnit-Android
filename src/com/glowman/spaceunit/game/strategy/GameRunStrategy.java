package com.glowman.spaceunit.game.strategy;

import android.util.Log;
import com.glowman.android.framework.Graphics;
import com.glowman.android.framework.Input;
import com.glowman.android.framework.math.Vector2;
import com.glowman.spaceunit.game.mapObject.Enemy;
import com.glowman.spaceunit.game.mapObject.MapObjectImagesENUM;
import com.glowman.spaceunit.game.mapObject.Ship;

import java.lang.Math;
import java.lang.Override;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class GameRunStrategy extends GameStrategy {

	public GameRunStrategy(Graphics graphics, Ship ship)
	{
		super(graphics, ship);
	}

	@Override
	public void createEnemy()
	{
		if (_enemies == null)
		{
			_enemies = new ArrayList<Enemy>();
		}
		int imgsSize = MapObjectImagesENUM.SPACE_OBJECTS.length;
		String imgStr = MapObjectImagesENUM.SPACE_OBJECTS[(int) (Math.random() * imgsSize)];
		Vector2 screenSize = new Vector2(_graphics.getWidth(), _graphics.getHeight());
		Enemy enemy = new Enemy(_graphics.newPixmap(imgStr), screenSize, true);
		enemy.setRandomBorderPosition();
		enemy.setRandomGeneralSpeed();
		_enemies.add(enemy);
	}

	@Override
	public void update()
	{
		if (_heroShip.isMoving())
		{
			_heroShip.moveTo(_heroShip.getTargetPosition().x, _heroShip.getTargetPosition().y);
			_heroShip.rotateTo(_heroShip.getTargetPosition().x, _heroShip.getTargetPosition().y);
		}

		if (Math.random() < .03) { this.createEnemy(); }
		if (_enemies != null)
		{
			for (Enemy enemy : _enemies)
			{
				enemy.moveTo(_heroShip.getPosition().x, _heroShip.getPosition().y);
			}
		}
		if (_enemies != null)
		{
			this.checkEnemyHits();
		}
	}

	@Override
	public void touchesBegan(List<Input.TouchEvent> touches) {
		if (touches.size() == 0) { throw new Error("touches length cant be 0 here"); }
		Input.TouchEvent touch = touches.get(0);
		_heroShip.setTargetPosition(new Vector2(touch.x, touch.y));
		_heroShip.setMoving(true);
	}

	public void touchesEnded(List<Input.TouchEvent> touches) {
		if (touches.size() == 0) { throw new Error("touches length cant be 0 here"); }
		_heroShip.setMoving(false);
	}

	public void touchesMoved(List<Input.TouchEvent> touches) {
		if (touches.size() == 0) { throw new Error("touches length cant be 0 here"); }
		Input.TouchEvent touch = touches.get(0);
		_heroShip.setTargetPosition(new Vector2(touch.x, touch.y));
		_heroShip.setMoving(true);
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

				distance = position1.dist(position2);
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
