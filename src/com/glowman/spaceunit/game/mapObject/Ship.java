package com.glowman.spaceunit.game.mapObject;

import android.graphics.Bitmap;
import android.util.Log;
import com.badlogic.gdx.graphics.Pixmap;
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
	private boolean _moving;
	private boolean _shooting;

	public Ship(Sprite image, Vector2 screenSize, int reloadTime)
	{
		super(image, screenSize, true, false);
		_reloadTime = reloadTime;
		_currentReloadTime = 0;
		_readyForShoot = false;
		_targetForShooting = new Vector2(0, 0);
		_targetPosition = new Vector2(0, 0);
		_moving = false;
		_shooting = false;
	}

	@Override
	public void tick(float deltaTime)
	{
		super.tick(deltaTime);
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
		super.moveTo(x, y);
		super.rotateTo(x, y);
	}

	public boolean isMoving() { return _moving; }
	public void setMoving(boolean value) {
		if (_moving != value) {
			_moving = value;
			if (!_moving) { Log.d("hz", "stop ship"); super.stop(); }
			if (_moving) { this.moveToTargetPoint(); }
		}
	}
	public boolean isShooting() { return _shooting; }
	public void setShooting(boolean value) { _shooting = value; }

	public boolean isReadyForShoot() { return _readyForShoot; }

	public Vector2 getTargetPosition() { return _targetPosition; }
	public void setTargetPosition(Vector2 value) {
		Log.d("hz", "set target position for move");
		_targetPosition = value;
		if (_moving) { this.moveToTargetPoint(); }
	}
	public void setTargetForShooting(Vector2 value) { _targetForShooting = value; }


	private void moveToTargetPoint() {
		this.moveTo(_targetPosition.x, _targetPosition.y);
		this.rotateTo(_targetPosition.x, _targetPosition.y);
	}

}
