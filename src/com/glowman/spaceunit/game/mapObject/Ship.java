package com.glowman.spaceunit.game.mapObject;

import android.graphics.Bitmap;
import com.glowman.android.framework.Pixmap;
import com.glowman.android.framework.math.Vector2;

/**
 *
 */
public class Ship extends MovingSpaceObject {

	private int _reloadTime;
	private int _currentReloadTime;
	private boolean _readyForShoot;
	private Vector2 _targetPosition;
	private Vector2 _targetForShooting;
	private boolean _moving;
	private boolean _shooting;

	public Ship(Pixmap image, Vector2 screenSize, int reloadTime)
	{
		super(image, screenSize, true);
		_reloadTime = reloadTime;
		_currentReloadTime = 0;
		_readyForShoot = false;
		_targetForShooting = new Vector2(0, 0);
		_targetPosition = new Vector2(0, 0);
		_moving = false;
		_shooting = false;
	}

	public void tick()
	{
		_readyForShoot = false;
		if (_moving)
		{
			this.moveTo(_targetPosition.x, _targetPosition.y);
			this.rotateTo(_targetPosition.x, _targetPosition.y);
		}

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

	public boolean isMoving() { return _moving; }
	public void setMoving(boolean value) { _moving = value; }
	public boolean isShooting() { return _shooting; }
	public void setShooting(boolean value) { _shooting = value; }

	public boolean isReadyForShoot() { return _readyForShoot; }

	public Vector2 getTargetPosition() { return _targetPosition; }
	public void setTargetPosition(Vector2 value) { _targetPosition = value; }
	public void setTargetForShooting(Vector2 value) { _targetForShooting = value; }


}
