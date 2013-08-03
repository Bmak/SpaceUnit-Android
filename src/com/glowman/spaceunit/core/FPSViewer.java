package com.glowman.spaceunit.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.glowman.spaceunit.Assets;

public class FPSViewer {
	
	private static Boolean _show;
	private static float _x;
	private static float _y;
	private static String _fps;
	
	public static void init(Boolean show) {
		_show = show;
		if (_show == false) { return; }
		
		_x = 0;
		_y = Assets.VIRTUAL_HEIGHT;
	}
	
	public static void draw(SpriteBatch spriteBatch) {
		if (_show == false) { return; }
		
		_fps = "fps: " + Gdx.graphics.getFramesPerSecond();

		BitmapFont font = FontProvider.getFont();
		Color prevColor = font.getColor();
		font.setColor(Color.RED);
		font.draw(spriteBatch, _fps, _x, _y);
		font.setColor(prevColor);
	}
}
