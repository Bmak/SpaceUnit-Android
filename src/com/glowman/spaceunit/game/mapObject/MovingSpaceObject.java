package com.glowman.spaceunit.game.mapObject;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.Settings;

/**
 *
 */
public class MovingSpaceObject extends SpaceObject {
	protected float _generalSpeed;
	private BORDER_BEHAVIOUR _borderBehaviour;

	protected float _vX;
	protected float _vY;
	protected float _frozenVX;
	protected float _frozenVY;
	protected float _rotationSpeed;

	protected boolean _paused;

	public MovingSpaceObject(Sprite image, boolean randomScale, BORDER_BEHAVIOUR borderBehaviour) {
		super(image, randomScale);
		_borderBehaviour = borderBehaviour;
		_generalSpeed = 0;
		_rotationSpeed = 0;
		this.setVelocity(0, 0);
		_paused = false;
	}

	public MovingSpaceObject(Sprite image) {
		this(image, false, BORDER_BEHAVIOUR.NONE);
	}

	public void setGeneralSpeed(float value) { _generalSpeed = value; }
	public void setRotationSpeed(float value) { _rotationSpeed = value; }

	public float getRotationSpeed() { return _generalSpeed; }

	public void moveTo(float targetX, float targetY)
	{
		float dx = targetX - getCenterPosition().x;
		float dy = targetY - getCenterPosition().y;
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

		float newX = _position.x + _vX * coef;
		float newY = _position.y + _vY * coef;

		if (_borderBehaviour == BORDER_BEHAVIOUR.STOP) {
			if (newX + this.getWidth() < Assets.FULL_VIRTUAL_WIDTH + Assets.FULL_X_OFFSET &&
					newX > Assets.FULL_X_OFFSET)
				_position.x = newX;
			if (newY + this.getHeight() < Assets.FULL_VIRTUAL_HEIGHT + Assets.FULL_Y_OFFSET &&
					newY > Assets.FULL_Y_OFFSET)
				_position.y = newY;
		}
		else {
			_position.x  = newX;
			_position.y = newY;
		}
		_rotation+= _rotationSpeed * coef;
		if (_rotation > 360) { _rotation %= 360; }
		
		if (_borderBehaviour == BORDER_BEHAVIOUR.TELEPORT) {
			if (this.checkBorderHit())
			{
				this.setRandomBorderPosition();
			}
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
			float randomWidth = (float)Math.round(Math.random()); //0 or 1
			randomX = -this.getWidth() + Assets.FULL_X_OFFSET + randomWidth *
										(Assets.FULL_VIRTUAL_WIDTH + (this.getWidth())*2);
			randomY = -this.getHeight() + Assets.FULL_Y_OFFSET + (float)Math.random() *
										(Assets.FULL_VIRTUAL_HEIGHT + (this.getHeight())*2);
		}
		else
		{
			randomX = -this.getWidth() + Assets.FULL_X_OFFSET + (float)Math.random() *
										(Assets.FULL_VIRTUAL_WIDTH + (this.getWidth())*2);
			float randomHeight = (float) Math.round(Math.random());
			randomY = -this.getHeight() + Assets.FULL_Y_OFFSET + randomHeight *
										(Assets.FULL_VIRTUAL_HEIGHT + (this.getHeight())*2);
		}

		super.setPosition(randomX, randomY);
	}
	
	public boolean isMoving() { return _vX != 0 || _vY != 0; }
	
	@Override
	protected void setImage(Sprite image) {
		super.setImage(image);
		image.setRotation(_rotation);
	}

	protected void setVelocity(float vx, float vy) {
		_vX = vx;
		_vY = vy;
		_frozenVX = vx;
		_frozenVY = vy;
	}

	protected boolean checkBorderHit() {
		return ((_position.x + this.getWidth() < Assets.FULL_X_OFFSET) ||
				(_position.x - this.getWidth() + Assets.FULL_Y_OFFSET > Assets.FULL_VIRTUAL_WIDTH) ||
				(_position.y + this.getHeight() < Assets.FULL_Y_OFFSET) ||
				(_position.y - this.getHeight() + Assets.FULL_Y_OFFSET > Assets.FULL_VIRTUAL_HEIGHT));
	}

	public static enum BORDER_BEHAVIOUR {
		NONE,
		TELEPORT,
		STOP
	}

}

