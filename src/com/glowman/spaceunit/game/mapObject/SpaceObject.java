package com.glowman.spaceunit.game.mapObject;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 *
 */
public abstract class SpaceObject {

	protected final Vector2 _screenSize;
	private final float _scale;
	protected final Sprite _image;
	protected Vector2 _position;
	protected float _rotation;

	public SpaceObject(Sprite image, Vector2 screenSize, boolean randomScale) {
		_screenSize = screenSize;
		_scale = (float) (randomScale ? (Math.random() * .2) + .2 : 1);
		_image = image;
		_image.setScale(_scale);
		_position = new Vector2(0,0);
		_rotation = 0;
	}

	public Sprite getImage() { return _image; }

	public void setRandomPosition() {
		_position.x = (float)Math.random() * _screenSize.x;
		_position.y = (float)Math.random() * _screenSize.y;
	}

	public void setPosition(Vector2 value) { _position = value; }
	public Vector2 getPosition() { return _position; }

	public Vector2 getCenterPosition() {
		Vector2 result = this.getPosition().cpy();
		result.x += _image.getWidth()/2;
		result.y += _image.getHeight()/2;
		return result;
	}
}
