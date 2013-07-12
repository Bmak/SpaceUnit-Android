package com.glowman.spaceunit.game.mapObject;

import com.glowman.android.framework.Pixmap;
import com.glowman.android.framework.math.Vector2;

/**
 *
 */
public abstract class SpaceObject {

	protected final Vector2 _screenSize;
	private final int _scale;
	protected final Pixmap _image;
	protected Vector2 _position;
	protected float _rotation;

	public SpaceObject(Pixmap image, Vector2 screenSize, boolean randomScale)
	{
		_screenSize = screenSize;
		_scale = randomScale ? ((int)Math.random() * 50) + 50 : 50;
		_image = image;
		_image.setScale(_scale);
		_position = new Vector2(0, 0);
		_rotation = 0;
	}

	public Pixmap getImage() { return _image; }

	public void setRandomPosition()
	{
		_position.x = (float)Math.random() * _screenSize.x;
		_position.y = (float)Math.random() * _screenSize.y;
	}

	public void setPosition(Vector2 value) { _position = value; }
	public Vector2 getPosition() { return _position; }
}
