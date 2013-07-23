package com.glowman.spaceunit.game.mapObject;

import android.util.Log;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 *
 */
public class Enemy extends MovingSpaceObject {

	protected float _speedX;
	protected float _speedY;
	protected float _speedRotate;
	private boolean _teleportOnBorder;

	public Enemy(Sprite image, Vector2 screenSize, boolean teleportOnBorder)
	{
		super(image, screenSize);
		_teleportOnBorder = teleportOnBorder;
		_speedRotate = 0;
		_speedX = 0;
		_speedY = 0;
	}

	public Enemy(Sprite image, Vector2 screenSize)
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
	
	public void tick(float deltaTime) {
		checkBorderTeleport();
		
		_image.setPosition(_position.x, _position.y);
		_image.setRotation(_rotation);
	}
	
	public void setRandomBehaviour()
	{
		_speedX = 5*(float)Math.random() * 2 - 1;
		_speedY = 5*(float)Math.random() * 2 - 1;
		_speedRotate = 5*((float)Math.random() * 2 - 1);
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
			 (_position.x - _image.getWidth() > _screenSize.x) ||
			 (_position.y + _image.getHeight() < 0) ||
			 (_position.y - _image.getHeight() > _screenSize.y))
		{
			this.setRandomBorderPosition();
		}
	}
}
