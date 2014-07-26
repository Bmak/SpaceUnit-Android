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
import com.glowman.spaceunit.game.mapObject.hero.Ship;
//import com.glowman.spaceunit.game.mapObject.enemy.behaviour.core.TouchPad;
import com.glowman.spaceunit.game.strategy.IGameStrategy;

/**
 *
 */
public class GameTouchListener extends InputAdapter {
    private IGameStrategy _gameStrategy;
    private Game _game;
    private Button _pauseBtn;
    private AbilityButton _abilityButton;
    private Ship _ship;
    private TextButton _toMenuBtn;

    private boolean _gameOver;

    GameTouchListener(Game game, Button pauseBtn, TextButton toMenuBtn) {
        _game = game;
        _pauseBtn = pauseBtn;
        _gameOver = false;
        _toMenuBtn = toMenuBtn;
    }

    public void setShip(Ship ship) {
        _ship = ship;
    }

    public void init(IGameStrategy gameStrategy, AbilityButton abilityButton) {
        _gameStrategy = gameStrategy;
        _gameOver = false;
        _abilityButton = abilityButton;
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

                //click abil button
                if (_abilityButton.isReady() &&
                        _abilityButton.getView().getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                    _gameStrategy.useAbility();
                }
                //click to scene
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
        if (!_gameStrategy.isPaused()) {
            _gameStrategy.touchUp(new TouchEvent(TouchEvent.TOUCH_UP, screenX, screenY, pointer));

        } else {
            //in pause
            Vector3 touchPoint = CoordinatesTranslator.toVirtualView(screenX, screenY);
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



