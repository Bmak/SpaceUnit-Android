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

	protected final Vector2 _screenSize;
	private float _scale;
	protected final Sprite _image;
	protected Vector2 _position;
	protected float _rotation;
	protected float _width;
	protected float _height;

	public SpaceObject(Sprite image, Vector2 screenSize, boolean randomScale) {
		_screenSize = screenSize;
		_scale = (float) (randomScale ? (Math.random() * (scaleMax - scaleMin)) + scaleMin : 1);
		_image = image;
		//TODO WTF???
		//_image.setScale(_scale);
		_width = _image.getWidth() / Assets.pixelDensity;
		_height = _image.getHeight() / Assets.pixelDensity;
		_image.setSize(_width*_scale, _height*_scale);

		_image.setOrigin(_image.getWidth()/2, _image.getHeight()/2);
		
		_position = new Vector2(0,0);
		_rotation = 0;
	}
	
	//TODO metodi setSize() & setScale() ne optimal'ni, teryaetsa scale
	public void setSize(float width, float height) {
		_image.setSize(width, height);
		_width = width;
		_height = width;
	}
	
	public void setScale(float scale) {
		_scale = scale;
		_image.setSize(_width*_scale, _height*_scale);
	}
	
	public Sprite getImage() { return _image; }

	public void setRandomPosition() {
		_position.x = (float)Math.random() * _screenSize.x;
		_position.y = (float)Math.random() * _screenSize.y;
		_image.setPosition(_position.x, _position.y);
	}

	public void setPosition(Vector2 value) {
		_position = value;
		_image.setPosition(_position.x, _position.y);
	}
	public Vector2 getPosition() { return _position; }

	public Vector2 getCenterPosition() {
		Vector2 result = this.getPosition().cpy();
		result.x += _image.getWidth()/2;
		result.y += _image.getHeight()/2;
		return result;
	}
}
