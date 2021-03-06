package com.glowman.spaceunit.game.mapObject;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.SoundPlayer;
import com.glowman.spaceunit.game.animation.IBlowMaker;

/**
 *
 */
public abstract class SpaceObject {

	public static double scaleMin = .6d;
	public static double scaleMax = 1.5d;
	public static double scaleNorm = 1d;

	private float _scale;
	protected Sprite _image;
	protected Vector2 _position;
	protected float _rotation;
	protected float _width;
	protected float _height;

	protected boolean _isDead;

	public SpaceObject(Sprite image, boolean randomScale) {
		_scale = (float) (randomScale ? (Math.random() * (scaleMax - scaleMin)) + scaleMin : 1);
		_position = new Vector2(0,0);
		_rotation = 0;
		_isDead = false;

		this.setImage(image);
	}

	public void tick(float delta) {
	}

	public void setDead() {
		_isDead = true;
	}
	public boolean isDead() { return _isDead; }
	
	public float getWidth() { return _width * _scale; }
	public float getHeight() { return _height * _scale; }

	public float basicWidth() { return _width; }
	public float basicHeight() { return _height; }

	public void setScale(float scale) {
		_scale = scale;
		this.updateImageSize();
		this.updateImageOrigin();
	}

	public float getScale() { return _scale; }

	public void setAlpha(float alpha) {
		Color color = _image.getColor();
		color.a = alpha;
		_image.setColor(color);
	}
	
	public Sprite getImage() { return _image; }

	public void setRandomPosition() {
		_position.x = (float)Math.random() * Assets.VIRTUAL_WIDTH;
		_position.y = (float)Math.random() * Assets.VIRTUAL_HEIGHT;
		this.updateImagePosition();
	}

	public void setPosition(float x, float y)
	{
		_position.x = x;
		_position.y = y;
		this.updateImagePosition();
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

	public void explode(IBlowMaker blowMaker) {
		blowMaker.makeBlow(this.getCenterPosition().x, this.getCenterPosition().y, this.getWidth());
		SoundPlayer.playSound(Assets.bamSound);
	}

	protected void setImage(Sprite image)
	{
		_image = image;
		_width = image.getWidth();
		_height = image.getHeight();
		this.updateImagePosition();
		this.updateImageSize();
		this.updateImageOrigin();
	}

	private void updateImagePosition() {
		_image.setPosition(_position.x, _position.y);
	}
	private void updateImageSize() {
		_image.setSize(this.getWidth(), this.getHeight());
	}
	private void updateImageOrigin() {
		_image.setOrigin(_width*_scale/2, _height*_scale/2);
	}
}
