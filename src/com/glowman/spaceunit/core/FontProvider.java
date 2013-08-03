package com.glowman.spaceunit.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.glowman.spaceunit.Assets;

/**
 *
 */
public class FontProvider {
	private static BitmapFont _font = null;

	public static BitmapFont getFont() {
		if (_font == null) {
			_font = new BitmapFont(Gdx.files.internal(Assets.gameFontPath), Assets.gameFontRegion, false);
		}
		return _font;
	}
}
