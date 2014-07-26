package com.glowman.spaceunit.game.mapObject;


import android.util.Log;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.Settings;
import com.glowman.spaceunit.game.mapObject.hero.Ship;

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

	private int SOFT_TOUCH_DISTANCE = 40;

	public static enum BORDER_BEHAVIOUR {
		NONE,
		TELEPORT,
		MIRROR_TELEPORT,
		STOP,
		SOFT_TOUCH
	}

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

		if (_borderBehaviour == BORDER_BEHAVIOUR.SOFT_TOUCH) this.checkSoftBorderBehaviour();

		float newX = _position.x + _vX * coef;
		float newY = _position.y + _vY * coef;

		_rotation+= _rotationSpeed * coef;
		if (_rotation > 360) { _rotation %= 360; }

		this.checkBorderBehaviour(newX, newY);

		super._image.setPosition(_position.x, _position.y);
		_image.setRotation(_rotation);
	}

	/**
	 * Проверяет близость к границе экрана и выполняет заданное поведение
	 * @param newX предполагаемый X после текущего tick
	 * @param newY предполагаемый Y после текущего tick
	 */
	private void checkBorderBehaviour(float newX, float newY) {
		if (_borderBehaviour == BORDER_BEHAVIOUR.STOP) {
			if (!this.isTouchedBorderByX(newX)) _position.x = newX;
			if (!this.isTouchedBorderByY(newY)) _position.y = newY;
		}
		else {
			_position.x  = newX;
			_position.y = newY;
		}

		if (_borderBehaviour == BORDER_BEHAVIOUR.TELEPORT) {
			if (this.isOutOfBorder())
			{

                this.setRandomBorderPosition();
			}
		}
		else if (_borderBehaviour == BORDER_BEHAVIOUR.MIRROR_TELEPORT) {
			if (this.isOutOfBorder())
			{
				this.setMirrorBorderPosition();
			}
		}

	}

	public void rotateTo(float targetX, float targetY)
	{
		float dx = _position.x - targetX;
		float dy = _position.y - targetY;
		float angle = 180 + (float)Math.atan2(dy, dx) * 180 / 3.14f;
		_rotation = angle;
	}

	/**
	 * Проверяет близость к границе на SOFT_TOUCH_DISTANCE
	 * и замедляет движение в этом районе, если скорость направлена к границе
	 */
    private float distanceToLeft(float x)
    {
        float leftDistance =x-Assets.FULL_X_OFFSET;


        return leftDistance;
    }
    private float distanceToRight(float x){
        float rightDistance = Assets.FULL_VIRTUAL_WIDTH-x+Assets.FULL_X_OFFSET-this.getWidth();

        return  rightDistance;
    }
    private float distanceToTop(float y){
        float topDistance = Assets.FULL_VIRTUAL_HEIGHT-y+Assets.FULL_Y_OFFSET-this.getHeight();

        return topDistance;
    }
    private float distanceToBottom(float y){
        float bottomDistance = y-Assets.FULL_Y_OFFSET;

        return bottomDistance;
    }
	private void checkSoftBorderBehaviour()
	{
		float left = this.distanceToLeft(_position.x);
		float right = this.distanceToRight(_position.x);
		float top = this.distanceToTop(_position.y);
		float bottom = this.distanceToBottom(_position.y);

		float minDistance = Math.min(left, Math.min(right, Math.min(top, bottom)));

		if (minDistance < SOFT_TOUCH_DISTANCE) {
			if ((minDistance == left && _vX < 0) ||
				(minDistance == right && _vX > 0) ||
				(minDistance == top && _vY > 0) ||
				(minDistance == bottom && _vY < 0))
			{

				float multiplier = minDistance / SOFT_TOUCH_DISTANCE;
				_vX = _frozenVX * multiplier;
				_vY = _frozenVY * multiplier;
			}
		}
	}

	/**
	 * Объект вылетает зеркально из противоположной стенки
	 */
	private void setMirrorBorderPosition()
	{
		boolean isOutToRight =Assets.FULL_VIRTUAL_WIDTH-_position.x+Assets.FULL_X_OFFSET-this.getWidth()<0;
		boolean isOutToLeft = _position.x + this.getWidth() < Assets.FULL_X_OFFSET;
		boolean isOutToTop = _position.y + this.getHeight() < Assets.FULL_Y_OFFSET;
		boolean isOutToBottom = _position.y  + Assets.FULL_Y_OFFSET > Assets.FULL_VIRTUAL_HEIGHT;

		if (isOutToRight) super.setPosition(Assets.FULL_X_OFFSET, _position.y);
		else if (isOutToLeft) super.setPosition(Assets.VIRTUAL_WIDTH- Assets.FULL_X_OFFSET, _position.y);
		else if (isOutToTop) super.setPosition(_position.x, Assets.VIRTUAL_HEIGHT-Assets.FULL_Y_OFFSET);
		else if (isOutToBottom) super.setPosition(_position.x, Assets.FULL_Y_OFFSET);
	}

	/**
	 * Объект вылетает из рандомного места на границе экрана
	 */
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

	private boolean isTouchedBorderByX(float x) {
		return !(x + this.getWidth() < Assets.FULL_VIRTUAL_WIDTH + Assets.FULL_X_OFFSET &&
				x > Assets.FULL_X_OFFSET);
	}
	private boolean isTouchedBorderByY(float y) {
		return !(y + this.getHeight() < Assets.FULL_VIRTUAL_HEIGHT + Assets.FULL_Y_OFFSET &&
				y > Assets.FULL_Y_OFFSET);
	}

	//TODO check this
	protected boolean isOutOfBorder() {




        return ((_position.x  + this.getWidth() < Assets.FULL_X_OFFSET) ||
				(_position.x - this.getWidth() + Assets.FULL_Y_OFFSET > Assets.FULL_VIRTUAL_WIDTH) ||
				(_position.y + this.getHeight() < Assets.FULL_Y_OFFSET) ||
				(_position.y + Assets.FULL_Y_OFFSET > Assets.FULL_VIRTUAL_HEIGHT));

	}

}

//boolean isOutToRight = _position.x - this.getWidth() + Assets.FULL_Y_OFFSET > Assets.FULL_VIRTUAL_WIDTH;
//boolean isOutToLeft = _position.x   < Assets.FULL_X_OFFSET;
//boolean isOutToTop = _position.y  < Assets.FULL_Y_OFFSET;
//boolean isOutToBottom = _position.y - this.getHeight() + Assets.FULL_Y_OFFSET > Assets.FULL_VIRTUAL_HEIGHT;
