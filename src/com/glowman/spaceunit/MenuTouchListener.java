package com.glowman.spaceunit;

import android.util.Log;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.glowman.spaceunit.game.GameScreen;
import com.glowman.spaceunit.game.core.Button;
import com.glowman.spaceunit.game.strategy.GameStrategy;

/**
 *
 */
public class MenuTouchListener extends InputAdapter {
	private Game _game;
	private Button _playBtnRun;
	private Button _playBtnShoot;
	private Vector3 _touchPoint;

	//coz touch up can fire just after screen changing, and touch down was into previous screen...
	private boolean _wasTouchDown = false;

	public MenuTouchListener(Game game, Button playBtnRun, Button playBtnShoot)
	{
		_game = game;
		_playBtnRun = playBtnRun;
		_playBtnShoot = playBtnShoot;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		_wasTouchDown = true;
		if (this.isButtonUnderPoint(_playBtnRun)) {
			_playBtnRun.setClickedMode();
		} else {
			_playBtnRun.setNormalMode();
		}
		
		if (this.isButtonUnderPoint(_playBtnShoot)) {
			_playBtnShoot.setClickedMode();
		} else {
			_playBtnShoot.setNormalMode();
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (!_wasTouchDown) { return false; }
		_wasTouchDown = false;
		_playBtnRun.setNormalMode();
		_playBtnShoot.setNormalMode();

		if (this.isButtonUnderPoint(_playBtnRun) || this.isButtonUnderPoint(_playBtnShoot))
		{
			Gdx.input.setInputProcessor(null);
			int gameType = this.isButtonUnderPoint(_playBtnRun) ? GameStrategy.RUN_GAME : GameStrategy.SHOOT_GAME;
			_game.setScreen(new GameScreen(_game, gameType));
		}
		return false;
	}

	/**
	 * Если точка текущего тача находится в пределах button, то возвращаем true
	 * @param button
	 * @return
	 */
	private boolean isButtonUnderPoint(Button button)
	{
		_touchPoint = CoordinatesTranslator.toVirtualView(Gdx.input.getX(), Gdx.input.getY());

		if (button.getView().getBoundingRectangle().contains(_touchPoint.x, _touchPoint.y)) {
			return true;
		}
		return false;
	}
	
	
}
