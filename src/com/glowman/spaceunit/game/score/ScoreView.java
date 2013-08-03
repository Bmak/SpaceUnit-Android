package com.glowman.spaceunit.game.score;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.core.FontProvider;

/**
 *
 */
public class ScoreView extends Sprite {
	private final String SCORE_TEXT = "Score :";

	private float _score;

	public ScoreView(float score) {
		super(Assets.getSimpleBtnRegion(1));
		_score = score;
	}

	public ScoreView() {
		this(0);
	}

	public void updateScore(float score) {
		_score = score;
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		super.draw(spriteBatch);
		BitmapFont font = FontProvider.getFont();
		Color prevColor = font.getColor();
		font.setColor(Color.RED);
		BitmapFont.TextBounds bounds = font.getBounds(SCORE_TEXT);
		float x = super.getX() + 10;
		float y = super.getY() + (getHeight() + bounds.height)/2;
		font.draw(spriteBatch, SCORE_TEXT, x, y);

		String scoreValue = String.valueOf(_score);
		bounds = font.getBounds(scoreValue);
		x = super.getX() + super.getWidth() / 2 + 10;

		font.draw(spriteBatch, scoreValue, x, y);

		font.setColor(prevColor);
	}
}
