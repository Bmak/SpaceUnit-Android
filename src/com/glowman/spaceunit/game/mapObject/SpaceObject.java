package com.glowman.spaceunit.game.mapObject;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.Assets;

/**
 *
 */
public abstract class SpaceObject {

	public static double scaleMin = .2d;
	public static double scaleMax = .4d;

	private float _scale;
	protected final Sprite _image;
	protected Vector2 _position;
	protected float _rotation;
	protected float _width;
	protected float _height;

	public SpaceObject(Sprite image, boolean randomScale) {
		_scale = (float) (randomScale ? (Math.random() * (scaleMax - scaleMin)) + scaleMin : 1);
		_image = image;
		this.setSize(_image.getWidth()/Assets.pixelDensity, _image.getHeight()/Assets.pixelDensity);

		_position = new Vector2(0,0);
		_rotation = 0;
	}
	
	public float getWidth() { return _width * _scale; }
	public float getHeight() { return _height * _scale; }

	public float basicWidth() { return _width; }
	public float basicHeight() { return _height; }

	public void setSize(float width, float height) {
		_width = width;
		_height = height;
		_image.setSize(_width * _scale, _height * _scale);
		this.updateOrigin();
	}

	public void setScale(float scale) {
		_scale = scale;
		_image.setSize(_width*_scale, _height*_scale);
		this.updateOrigin();
	}

	public float getScale() { return _scale; }
	
	public Sprite getImage() { return _image; }

	public void setRandomPosition() {
		_position.x = (float)Math.random() * Assets.VIRTUAL_WIDTH;
		_position.y = (float)Math.random() * Assets.VIRTUAL_HEIGHT;
		_image.setPosition(_position.x, _position.y);
	}

	public void setPosition(float x, float y)
	{
		_position.x = x;
		_position.y = y;
		_image.setPosition(x, y);
	}
	public void setPosition(Vector2 value) {
		this.setPosition(value.x, value.y);
	}
	public Vector2 getPosition() { return _position; }

	public Vector2 getCenterPosition() {
		Vector2 result = this.getPosition().cpy();
		result.x += _image.getOriginX();
		result.y += _image.getOriginY();
		return result;
	}

	private void updateOrigin() {
		_image.setOrigin(_width*_scale/2, _height*_scale/2);
	}
}
