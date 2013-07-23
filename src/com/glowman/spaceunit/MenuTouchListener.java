package com.glowman.spaceunit;

import android.util.Log;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.glowman.spaceunit.game.GameScreen;
import com.glowman.spaceunit.game.core.Button;

/**
 *
 */
public class MenuTouchListener extends InputAdapter {
	private Game _game;
	private Button _playBtnRun;
	private Button _playBtnShoot;

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
		Log.d("hz", "touch down!!!");
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
		/*if (!_wasTouchDown) { return false; }
		_wasTouchDown = false;
		Log.d("hz", "touch up!!!");
		_playBtnRun.setNormalMode();
		_playBtnShoot.setNormalMode();

		if (this.isButtonUnderPoint(_playBtnRun) || this.isButtonUnderPoint(_playBtnShoot))
		{
			Gdx.input.setInputProcessor(null);
			_game.setScreen(new GameScreen(_game));
		}*/
		return false;
	}

	/**
	 * Если точка текущего тача находится в пределах button, то возвращаем true
	 * @param button
	 * @return
	 */
	private boolean isButtonUnderPoint(Button button)
	{
		boolean result = false;
		Vector3 touchPoint = new Vector3();
		touchPoint.x = Gdx.input.getX();
		touchPoint.y = Gdx.input.getY();

		if (button.getView().getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
			result = true;
		}
		return result;
	}


}
