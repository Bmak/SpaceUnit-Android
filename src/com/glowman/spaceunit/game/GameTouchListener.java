package com.glowman.spaceunit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.glowman.spaceunit.game.core.TouchEvent;
import com.glowman.spaceunit.game.strategy.GameStrategy;

/**
 *
 */
public class GameTouchListener extends InputAdapter {
	private GameStrategy _gameStrategy;

	GameTouchListener(GameStrategy gameStrategy) {
		_gameStrategy = gameStrategy;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		_gameStrategy.touchDown(new TouchEvent(TouchEvent.TOUCH_DOWN, Gdx.input.getX(), Gdx.input.getY(), pointer));
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		_gameStrategy.touchUp(new TouchEvent(TouchEvent.TOUCH_UP, screenX, screenY, pointer));
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		_gameStrategy.touchMove(new TouchEvent(TouchEvent.TOUCH_UP, Gdx.input.getX(), Gdx.input.getY(), pointer));
		return false;
	}
}
