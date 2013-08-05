package com.glowman.spaceunit.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.glowman.spaceunit.CoordinatesTranslator;
import com.glowman.spaceunit.core.Button;
import com.glowman.spaceunit.core.ScreenControl;
import com.glowman.spaceunit.core.TouchEvent;
import com.glowman.spaceunit.game.mapObject.Ship;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.core.TouchPad;
import com.glowman.spaceunit.game.strategy.IGameStrategy;

/**
 *
 */
public class GameTouchListener extends InputAdapter {
	private IGameStrategy _gameStrategy;
	private Game _game;
	private Button _pauseBtn;
	private TouchPad _touchpad;
	private Ship _ship;

	private boolean _gameOver;

	GameTouchListener(Game game, Button pauseBtn,
					  TouchPad touchPad) {
		_game = game;
		_pauseBtn = pauseBtn;
		_gameOver = false;
		_touchpad = touchPad;
	}

	public void setShip(Ship ship) {
		_ship = ship;
	}

	public void init(IGameStrategy gameStrategy) {
		_gameStrategy = gameStrategy;
		_gameOver = false;
	}

	public void gameOver() {
		_gameOver = true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (_gameOver) {
			_game.setScreen(ScreenControl.getScreen(ScreenControl.MAIN));
		}  else {
			Vector3 touchPoint = CoordinatesTranslator.toVirtualView(screenX, screenY);

			if (_pauseBtn.getView().getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
				//pause
				this.processPauseBtn();
			} else {
				if (_touchpad.hit(touchPoint.x, touchPoint.y)) {
					_touchpad.touchDown(touchPoint.x, touchPoint.y);
					if (_ship != null) {
						_ship.moveOn(_touchpad.getKnobPercentX(), _touchpad.getKnobPercentY());
					}
				} else {
					_gameStrategy.touchDown(new TouchEvent(TouchEvent.TOUCH_DOWN, (int)touchPoint.x, (int)touchPoint.y, pointer));
				}
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector3 touchPoint = CoordinatesTranslator.toVirtualView(screenX, screenY);
		_touchpad.touchUp(touchPoint.x, touchPoint.y);
		if (_ship != null) {
			_ship.stopMoving();
		}
		_gameStrategy.touchUp(new TouchEvent(TouchEvent.TOUCH_UP, screenX, screenY, pointer));
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector3 touchPoint = CoordinatesTranslator.toVirtualView(screenX, screenY);
		if (_touchpad.hit(touchPoint.x, touchPoint.y)) {
			_touchpad.touchMove(touchPoint.x, touchPoint.y);
			if (_ship != null) {
				_ship.moveOn(_touchpad.getKnobPercentX(), _touchpad.getKnobPercentY());
			}
		}
		else {
			_gameStrategy.touchMove(new TouchEvent(TouchEvent.TOUCH_UP, (int)touchPoint.x, (int)touchPoint.y, pointer));
		}
		return false;
	}

	private void processPauseBtn() {
		if (_gameStrategy.isPaused()) {
			_gameStrategy.resumeGame();
			_pauseBtn.setNormalMode();
		} else {
			_gameStrategy.pauseGame();
			_pauseBtn.setClickedMode();
		}
	}
}
