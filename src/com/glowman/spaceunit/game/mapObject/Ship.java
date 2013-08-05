package com.glowman.spaceunit.game.mapObject;

import android.util.Log;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 *
 */
public class Ship extends MovingSpaceObject {

	private int _reloadTime;
	private int _currentReloadTime;
	private boolean _readyForShoot;
	private Vector2 _targetPosition;
	private Vector2 _targetForShooting;
	private boolean _shooting;

	public Ship(Sprite image, int reloadTime)
	{
		super(image, false, BORDER_BEHAVIOUR.STOP);
		_reloadTime = reloadTime;
		_currentReloadTime = 0;
		_readyForShoot = false;
		_targetForShooting = new Vector2(0, 0);
		_targetPosition = new Vector2(0, 0);
		_shooting = false;
	}

	@Override
	public void tick(float deltaTime)
	{
		if (!super.checkBorderHit()) {
			super.tick(deltaTime);
		}
		_readyForShoot = false;
		if (_shooting)
		{
			if (_currentReloadTime >= _reloadTime)
			{
				_readyForShoot = true;
				_currentReloadTime = 0;
			}
			else
			{
				_currentReloadTime++;
			}
		}
	}

	@Override
	public void moveTo(float x, float y)
	{
		//super.moveTo(x, y);
		//super.rotateTo(x, y);
	}

	public void moveOn(float dx, float dy) {
		float h = (float) Math.sqrt((double) (dx * dx + dy * dy) );
		float vx = dx / h * _generalSpeed;
		float vy = dy / h * _generalSpeed;

		super.setVelocity( vx, vy);
		super.rotateTo(_position.x + vx, _position.y + vy);
	}

	public boolean isShooting() { return _shooting; }
	public void setShooting(boolean value) { _shooting = value; }

	public boolean isReadyForShoot() { return _readyForShoot; }

	public void setTargetForShooting(Vector2 value) { _targetForShooting = value; }


	private void moveToTargetPoint() {

	}

}
