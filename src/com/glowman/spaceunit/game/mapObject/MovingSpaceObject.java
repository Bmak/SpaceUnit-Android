package com.glowman.spaceunit.game.mapObject;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.Settings;

/**
 *
 */
public class MovingSpaceObject extends SpaceObject {
	private float _generalSpeed;
	private boolean _teleportOnBorder;

	protected float _vX;
	protected float _vY;
	protected float _frozenVX;
	protected float _frozenVY;
	protected float _rotationSpeed;

	protected boolean _paused;

	public MovingSpaceObject(Sprite image, boolean randomScale, boolean teleportOnBorder) {
		super(image, randomScale);
		_teleportOnBorder = teleportOnBorder;
		_generalSpeed = 0;
		_rotationSpeed = 0;
		this.setVelocity(0, 0);
		_paused = false;
	}

	public MovingSpaceObject(Sprite image) {
		this(image, false, false);
	}

	public void setGeneralSpeed(float value) { _generalSpeed = value / Assets.pixelDensity; }
	public void setRotationSpeed(float value) { _rotationSpeed = value / Assets.pixelDensity; }

	public float getRotationSpeed() { return _generalSpeed * Assets.pixelDensity; }

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
		this.setVelocity(vx, vy);
	}

	public void stopMoving()
	{
		_vX = 0;
		_vY = 0;
	}

	public void resumeMoving() {
		_vX = _frozenVX;
		_vY = _frozenVY;
	}

	@Override
	public void tick(float delta) {
		super.tick(delta);
		float coef = delta / Settings.FRAME_TIME;

		if (_isDead) { return; }
		if (_paused) { return; }

		_position.x += _vX * coef;
		_position.y += _vY * coef;
		_rotation+= _rotationSpeed * coef;
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

	@Override
	protected void setImage(Sprite image) {
		super.setImage(image);
		image.setRotation(_rotation);
	}

	private void setVelocity(float vx, float vy) {
		_vX = vx;
		_vY = vy;
		_frozenVX = vx;
		_frozenVY = vy;
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
