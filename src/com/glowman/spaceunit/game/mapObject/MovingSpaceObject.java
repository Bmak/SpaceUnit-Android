package com.glowman.spaceunit.game.mapObject;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.Assets;

/**
 *
 */
public class MovingSpaceObject extends SpaceObject {
	private float _generalSpeed;
	private boolean _teleportOnBorder;

	protected float _vX;
	protected float _vY;
	protected float _rotationSpeed;

	public MovingSpaceObject(Sprite image, boolean randomScale, boolean teleportOnBorder) {
		super(image, randomScale);
		_teleportOnBorder = teleportOnBorder;
		_generalSpeed = 0;
		_rotationSpeed = 0;
		_vX = 0;
		_vY = 0;
	}
	
	public MovingSpaceObject(Sprite image) {
		this(image, false, false);
	}

	public void setGeneralSpeed(float value) { _generalSpeed = value / Assets.pixelDensity; }
	public void setRotationSpeed(float value) { _rotationSpeed = value / Assets.pixelDensity; }

	public void moveTo(float targetX, float targetY)
	{
		float dx = targetX - _position.x;
		float dy = targetY - _position.y;
		float h = (float) Math.sqrt((double) (dx * dx + dy * dy) );
		float vx = 0;
		float vy = 0;
		if (h != 0){
			vx = (dx / h) * _generalSpeed;
			vy = (dy / h) * _generalSpeed;
		}
		_vX = vx;
		_vY = vy;
	}

	public void stop()
	{
		_vX = 0;
		_vY = 0;
	}

	public void tick(float delta) {
		if (_vX == 0 && _vY == 0) { return; }

		_position.x += _vX;
		_position.y += _vY;
		_rotation+= _rotationSpeed;
		if (_rotation > 360) { _rotation %= 360; }
		
		if (_teleportOnBorder) {
			checkBorderTeleport();
		}

		super._image.setPosition(_position.x, _position.y);
		_image.setRotation(_rotation);
	}

	public void rotateTo(float targetX, float targetY)
	{
		float dx = _position.x - targetX;
		float dy = _position.y - targetY;
		float angle = 180 + (float)Math.atan2(dy, dx) * 180 / 3.14f;
		_rotation = angle;
	}

	public void setRandomGeneralSpeed()
	{
		this.setGeneralSpeed( (float)Math.random() * 2f );
	}

	public void setRandomBehaviour()
	{
		this.setRandomGeneralSpeed();
		this.setRotationSpeed(5 * ((float)Math.random() * 2 - 1));
		this.moveTo((float)Math.random() * Assets.VIRTUAL_WIDTH,
					(float)Math.random() * Assets.VIRTUAL_HEIGHT);
	}

	public void setRandomBorderPosition()
	{
		float randomX, randomY;

		if (Math.random() < .5)
		{
			float randomWidth = (float)Math.round(Math.random());
			randomX = -this.getWidth() + randomWidth * (Assets.VIRTUAL_WIDTH + this.getWidth()*2);
			randomY = -this.getHeight() + (float)Math.random() * (Assets.VIRTUAL_HEIGHT + this.getHeight()*2);
		}
		else
		{
			randomX = -this.getWidth() + (float)Math.random() * (Assets.VIRTUAL_WIDTH + this.getWidth()*2);
			float randomHeight = (float) Math.round(Math.random());
			randomY = -this.getHeight() + randomHeight * (Assets.VIRTUAL_HEIGHT + this.getHeight()*2);
		}

		super.setPosition(randomX, randomY);
	}

	private void checkBorderTeleport()
	{
		if ((_position.x + this.getWidth() < 0) ||
				(_position.x - this.getWidth() > Assets.VIRTUAL_WIDTH) ||
				(_position.y + this.getHeight() < 0) ||
				(_position.y - this.getHeight() > Assets.VIRTUAL_HEIGHT))
		{
			this.setRandomBorderPosition();
		}
	}
}
