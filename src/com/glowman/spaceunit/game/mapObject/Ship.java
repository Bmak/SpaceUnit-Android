package com.glowman.spaceunit.game.mapObject;

import android.util.Log;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.core.AnimatedSprite;

/**
 *
 */
public class Ship extends MovingSpaceObject {

	private int _reloadTime;
	private int _currentReloadTime;
	private boolean _readyForShoot;
	private boolean _shooting;
	private AnimatedSprite _shipAnim;
	private TextureRegion _calmState;

	public Ship(Sprite image, int reloadTime)
	{
		super(image, false, BORDER_BEHAVIOUR.STOP);
		_calmState = Assets.ship;
		_reloadTime = reloadTime;
		_currentReloadTime = 0;
		_readyForShoot = false;
		_shooting = false;
		_shipAnim = new AnimatedSprite(Assets.shipArray, 0.1f, Animation.LOOP_PINGPONG);
	}

	@Override
	public void tick(float deltaTime)
	{
		_image.setRegion(_calmState);
		if (super.isMoving()) {
			_shipAnim.tick(deltaTime);
			_image.setRegion(_shipAnim.getCurrentRegion());
		}
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
	}

	@Override
	public void setDead() {
		super.setDead();
		_shooting = false;
	}

	public void moveOn(float dx, float dy) {
		float h = (float) Math.sqrt((double) (dx * dx + dy * dy) );
		float vx = dx / h * _generalSpeed;
		float vy = dy / h * _generalSpeed;

		super.setVelocity(vx, vy);
		super.rotateTo(_position.x + vx, _position.y + vy);
	}

	public boolean isShooting() { return _shooting; }
	public void setShooting(boolean value) { _shooting = value; }

	public boolean isReadyForShoot() { return _readyForShoot; }

}
