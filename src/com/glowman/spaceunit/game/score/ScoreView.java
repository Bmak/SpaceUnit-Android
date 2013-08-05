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

	private final float MAGIC_OFFSET = 15;

	private String _score;
	private String _scoreType;

	public ScoreView(String score) {
		super(Assets.getSimpleBtnRegion(1));
		_score = score;
		super.setSize(super.getWidth() * 1.3f, super.getHeight());
	}

	public ScoreView() {
		this("");
	}

	public void setScoreType(String scoreType) {
		_scoreType = scoreType;
	}

	public void setScore(String scoreValue) {
		_score = scoreValue;
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		super.draw(spriteBatch);
		BitmapFont font = FontProvider.getFont();
		Color prevColor = font.getColor();
		font.setColor(Color.RED);
		BitmapFont.TextBounds bounds = font.getBounds(SCORE_TEXT);
		float x = super.getX() + super.getWidth()/4 - bounds.width/2;
		float y = super.getY() + (getHeight() + bounds.height)/2;
		font.draw(spriteBatch, SCORE_TEXT, x, y);

		bounds = font.getBounds(_score);
		if (_scoreType == Score.SECONDS) {
			x = super.getX() + super.getWidth() / 2 + MAGIC_OFFSET;
		} else {
			x = super.getX() + super.getWidth() / 4 * 3 - bounds.width/2;
		}

		font.draw(spriteBatch, _score, x, y);

		font.setColor(prevColor);
	}
}
