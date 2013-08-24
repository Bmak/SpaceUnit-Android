package com.glowman.spaceunit.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.glowman.spaceunit.CoordinatesTranslator;
import com.glowman.spaceunit.core.Button;
import com.glowman.spaceunit.core.ScreenControl;
import com.glowman.spaceunit.core.TextButton;
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
	private Sprite _abilityButton;
	private TouchPad _touchpad;
	private Ship _ship;
	private TextButton _toMenuBtn;

	private boolean _gameOver;

	GameTouchListener(Game game, Button pauseBtn,
					  TouchPad touchPad, TextButton toMenuBtn,
					  Sprite abilityButton) {
		_game = game;
		_pauseBtn = pauseBtn;
		_gameOver = false;
		_touchpad = touchPad;
		_toMenuBtn = toMenuBtn;
		_abilityButton = abilityButton;
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
			} else if (!_gameStrategy.isPaused()) {
				if (_touchpad.hit(touchPoint.x, touchPoint.y)) {
					_touchpad.touchDown(touchPoint.x, touchPoint.y);
					if (_ship != null) {
						_ship.moveOn(_touchpad.getKnobPercentX(), _touchpad.getKnobPercentY());
					}
				} else if (_abilityButton.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
					_gameStrategy.useAbility();
				}
				else {
					_gameStrategy.touchDown(new TouchEvent(TouchEvent.TOUCH_DOWN, (int)touchPoint.x, (int)touchPoint.y, pointer));
				}
			} else {
				//in pause
				if (_toMenuBtn.getBounds().contains(touchPoint.x, touchPoint.y)) {
					_toMenuBtn.setClickedMode();
				}
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector3 touchPoint = CoordinatesTranslator.toVirtualView(screenX, screenY);
		if (!_gameStrategy.isPaused()) {
			_touchpad.touchUp(touchPoint.x, touchPoint.y);
			if (_ship != null) {
				_ship.stopMoving();
			}
			_gameStrategy.touchUp(new TouchEvent(TouchEvent.TOUCH_UP, screenX, screenY, pointer));
		} else {
			//in pause
			if (_toMenuBtn.getBounds().contains(touchPoint.x, touchPoint.y)) {
				_toMenuBtn.setNormalMode();
				_game.setScreen(ScreenControl.getScreen(ScreenControl.MAIN));
			}
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (!_gameStrategy.isPaused()) {
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
