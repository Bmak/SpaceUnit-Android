package com.glowman.spaceunit.game.mapObject;

import android.graphics.Bitmap;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

/**
 *
 */
public class MovingSpaceObject extends SpaceObject {
	private float _generalSpeed;

	public MovingSpaceObject(Pixmap image, Vector2 screenSize, boolean randomScale)
	{
		super(image, screenSize, randomScale);
		_generalSpeed = 0;
	}
	public MovingSpaceObject(Pixmap image, Vector2 screenSize)
	{
		this(image, screenSize, false);
	}

	public void setGeneralSpeed(float value) { _generalSpeed = value; }

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
		_position.x += vx;
		_position.y += vy;
	}
	public void rotateTo(float targetX, float targetY)
	{

	}

	public void setRandomGeneralSpeed()
	{
		_generalSpeed = (float)Math.random() * 2f;
	}

}
