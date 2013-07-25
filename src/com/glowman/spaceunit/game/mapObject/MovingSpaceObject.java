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

	public MovingSpaceObject(Sprite image, Vector2 screenSize, boolean randomScale, boolean teleportOnBorder) {
		super(image, screenSize, randomScale);
		_teleportOnBorder = teleportOnBorder;
		_generalSpeed = 0;
		_rotationSpeed = 0;
		_vX = 0;
		_vY = 0;
	}
	
	public MovingSpaceObject(Sprite image, Vector2 screenSize) {
		this(image, screenSize, false, false);
	}

	public void setGeneralSpeed(float value) { _generalSpeed = value; }
	public void setRotationSpeed(float value) { _rotationSpeed = value; }

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
		
		_rotation += _rotationSpeed;

		if (_teleportOnBorder) {
			checkBorderTeleport();
		}

		super._image.setPosition(_position.x, _position.y);
		//TODO yes. This is awesome pizdec :)
		//super._image.setRotation(_rotation);
		//_image.rotate(1/Assets.pixelDensity);
	}

	public void rotateTo(float targetX, float targetY)
	{
		
	}

	public void setRandomGeneralSpeed()
	{
		_generalSpeed = (float)Math.random() * 2f;
	}

	public void setRandomBehaviour()
	{
		this.setGeneralSpeed(((float)Math.random() * 2f + 0.5f) / Assets.pixelDensity);
		this.setRotationSpeed(5 * ((float)Math.random() * 2 - 1) / Assets.pixelDensity);
		this.moveTo((float)Math.random() * _screenSize.x,
					(float)Math.random() * _screenSize.y);
	}

	public void setRandomBorderPosition()
	{
		float randomX, randomY;

		if (Math.random() < .5)
		{
			randomX = -_image.getWidth() + (float)Math.round(Math.random()) * (_screenSize.x + _image.getWidth());
			randomY = -_image.getHeight() + (float)Math.random() * (_screenSize.y + _image.getHeight());
		}
		else
		{
			randomX = -_image.getWidth() + (float)Math.random() * (_screenSize.x + _image.getWidth());
			randomY = -_image.getHeight() + (float) Math.round(Math.random()) * (_screenSize.y + _image.getHeight());
		}

		_position.x = randomX;
		_position.y = randomY;
		this.setPosition(_position);
		setRandomBehaviour();
	}

	private void checkBorderTeleport()
	{
		if ((_position.x + _image.getWidth() < 0) ||
				(_position.x - _image.getWidth() > _screenSize.x) ||
				(_position.y + _image.getHeight() < 0) ||
				(_position.y - _image.getHeight() > _screenSize.y))
		{
			this.setRandomBorderPosition();
		}
	}
}
