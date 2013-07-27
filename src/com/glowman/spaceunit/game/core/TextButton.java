package com.glowman.spaceunit.game.core;

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
		
		_font = Assets.gameFont;
		_font.setColor(Color.RED);
		_font.setScale(2f/Assets.pixelDensity);
		_posText = new Vector2();
		this.setText(text);
	}
	
	public void setText(String text) {
		_text = text;
		TextBounds bounds = _font.getBounds(_text);
		_posText.set((getWidth() - bounds.width)/2, (getHeight() - bounds.height)/2);
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		super.draw(batch);
		_font.draw(batch, _text, _posText.x, _posText.y);
	}
}
