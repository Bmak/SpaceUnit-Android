package com.glowman.spaceunit.game.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.glowman.spaceunit.Assets;

public class TextButton extends Button {
	
	private BitmapFont _text;
	
	public TextButton(TextureRegion normal, TextureRegion clicked) {
		super(normal, clicked);
		
		_text = new BitmapFont(Gdx.files.internal(Assets.gameFontPath), Assets.gameFont, false);
	}
}
