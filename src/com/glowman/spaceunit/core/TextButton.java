package com.glowman.spaceunit.core;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.Assets;

public class TextButton extends Button {
	
	private String _text;
	private Vector2 _posText;
	private BitmapFont _font;
	
	public TextButton(TextureRegion normal, TextureRegion clicked, String text) {
		super(normal, clicked);
		
		_font = new BitmapFont(Gdx.files.internal(Assets.gameFontPath), Assets.gameFontRegion, false);
		_font.setColor(Color.RED);
		_font.setScale(1f/Assets.pixelDensity);
		_posText = new Vector2();
		this.setText(text);
	}
	
	public void setText(String text) {
		_text = text;
		TextBounds bounds = _font.getBounds(_text);
		float x = _currentView.getX() + (getWidth() - bounds.width)/2;
		float y = _currentView.getY() + (getHeight() + bounds.height)/2;
		_posText.set(x, y);
	}
	
	@Override
	public void setSize(float width, float height) {
		super.setSize(width, height);
		this.setText(_text);
	}
	
	@Override
	public void setScale(float scale) {
		super.setScale(scale);
		_font.setScale(_font.getScaleX()*_scale);
		this.setText(_text);
	}
	
	@Override
	public void setX(float x) {
		super.setX(x);
		this.setText(_text);
	}
	
	@Override
	public void setY(float y) {
		super.setY(y);
		this.setText(_text);
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		super.draw(batch);
		_font.draw(batch, _text, _posText.x, _posText.y);
	}
}
