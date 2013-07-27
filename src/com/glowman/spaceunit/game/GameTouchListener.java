package com.glowman.spaceunit.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.glowman.spaceunit.core.CameraHelper;
import com.glowman.spaceunit.core.TouchEvent;
import com.glowman.spaceunit.game.strategy.GameStrategy;

/**
 *
 */
public class GameTouchListener extends InputAdapter {
	private GameStrategy _gameStrategy;
	private OrthographicCamera _camera;

	GameTouchListener(OrthographicCamera camera, GameStrategy gameStrategy) {
		_gameStrategy = gameStrategy;
		_camera = camera;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 virtualPosition = CameraHelper.screenToViewport(_camera, screenX, screenY);
		_gameStrategy.touchDown(new TouchEvent(TouchEvent.TOUCH_DOWN, (int)virtualPosition.x, (int)virtualPosition.y, pointer));
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		_gameStrategy.touchUp(new TouchEvent(TouchEvent.TOUCH_UP, screenX, screenY, pointer));
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector3 virtualPosition = CameraHelper.screenToViewport(_camera, screenX, screenY);

		_gameStrategy.touchMove(new TouchEvent(TouchEvent.TOUCH_UP, (int)virtualPosition.x, (int)virtualPosition.y, pointer));
		return false;
	}
}
