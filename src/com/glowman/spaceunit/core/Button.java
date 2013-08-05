package com.glowman.spaceunit.core;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.glowman.spaceunit.Assets;

public class Button {
	protected final Sprite _normal;
	protected final Sprite _clicked;
	protected Sprite _currentView;
	protected float _width;
	protected float _height;
	protected float _scale;
	public int index = -1;

	public Button(TextureRegion normal, TextureRegion clicked)
	{
		_normal = new Sprite(normal);
		_clicked = new Sprite(clicked);
		_currentView = _normal;
		_width = _normal.getWidth();
		_height = _normal.getHeight();
		_scale = 1f;
		
	}

	public Sprite getView() { return _currentView; }
	public float getWidth() { return _width*_scale; }
	public float getHeight() { return _height*_scale; }

	public Rectangle getBounds() {
		return _currentView.getBoundingRectangle();
	}
	
	public void setClickedMode()
	{
		if (_currentView == _clicked) return;
		_currentView = _clicked;
		Assets.btnSound.play();
	}

	public void setNormalMode()
	{
		if (_currentView == _normal) return;
		_currentView = _normal;
	}
	
	public void draw(SpriteBatch batch) {
		_currentView.draw(batch);
	}

	public void draw(SpriteBatch batch, float alpha) {
		_currentView.draw(batch, alpha);
	}
	
	public void setSize(float width, float height) {
		_normal.setSize(width, height);
		_clicked.setSize(width, height);
		_width = width;
		_height = height;
		
		//TODO need?
		//_scale = 1f;
	}
	
	public void setScale(float scale)
	{
		_scale = scale;
		_normal.setSize(_width*_scale, _height*_scale);
		_clicked.setSize(_width*_scale, _height*_scale);
		//_normal.setScale(scale);
		//_clicked.setScale(scale);
	}

	public void setX(float x)
	{
		_normal.setX(x);
		_clicked.setX(x);
	}
	
	public void setY(float y)
	{
		_normal.setY(y);
		_clicked.setY(y);
	}

	public void setPosition(float x, float y) {
		this.setX(x);
		this.setY(y);
	}

}
