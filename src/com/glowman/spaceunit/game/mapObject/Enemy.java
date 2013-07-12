package com.glowman.spaceunit.game.mapObject;

import com.glowman.android.framework.Pixmap;
import com.glowman.android.framework.math.Vector2;

/**
 *
 */
public class Enemy extends MovingSpaceObject {

	private float _speedX;
	private float _speedY;
	private float _speedRotate;
	private boolean _teleportOnBorder;

	public Enemy(Pixmap image, Vector2 screenSize, boolean teleportOnBorder)
	{
		super(image, screenSize);
		_teleportOnBorder = teleportOnBorder;
		_speedRotate = 0;
		_speedX = 0;
		_speedY = 0;
	}

	public Enemy(Pixmap image, Vector2 screenSize)
	{
		this(image, screenSize, false);
	}

	public void setSpeedX(float value) { _speedX = value; }
	public void setSpeedY(float value) { _speedY = value; }
	public void setSpeedRotate(float value) { _speedRotate = value; }

	public void simpleMove()
	{
		_position.x += _speedX;
		_position.y += _speedY;
		_rotation += _speedRotate;
	}

	public void setRandomBehaviour()
	{
		double speedX = Math.random() * 2 - 1;
		double speedY = Math.random() * 2 - 1;
	}

	public void setRandomBorderPosition()
	{
		float randomX, randomY;

		if (Math.random() < .5)
		{
			randomX = (float)Math.round(Math.random()) * _screenSize.x;
			randomY = (float)Math.random() * _screenSize.y;
		}
		else
		{
			randomX = (float)Math.random() * _screenSize.x;
			randomY = (float) Math.round(Math.random()) * _screenSize.y;
		}
//		if (randomX == 0) { randomX = -this->getContentSize().width/2; }
//		if (randomX == _screenSize.width) { randomX += this->getContentSize().width/2; }
//		if (randomY == 0) { randomY = -this->getContentSize().height/2; }
//		if (randomY == _screenSize.height) { randomY += this->getContentSize().height/2; }

		_position.x = randomX;
		_position.y = randomY;
	}

	public void checkBorderTeleport()
	{
		if ((_position.x + _image.getWidth() < 0) ||
			 (_position.x > _screenSize.x) ||
			 (_position.y + _image.getHeight() < 0) ||
			 (_position.y > _screenSize.y))
		{
			this.setRandomBorderPosition();
		}
	}
}
