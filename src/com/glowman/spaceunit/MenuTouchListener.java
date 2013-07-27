package com.glowman.spaceunit;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.glowman.spaceunit.game.GameScreen;
import com.glowman.spaceunit.core.Button;
import com.glowman.spaceunit.core.ScreenControl;
import com.glowman.spaceunit.game.strategy.GameStrategy;

/**
 *
 */
public class MenuTouchListener extends InputAdapter {
	private Game _game;
	private Vector3 _touchPoint;
	private ArrayList<EventButton> _buttons;

	//coz touch up can fire just after screen changing, and touch down was into previous screen...
	private boolean _wasTouchDown = false;

	public MenuTouchListener(Game game)
	{
		_game = game;
		_buttons = new ArrayList<EventButton>();
	}
	
	public void addButton(Button button, int scrType) {
		EventButton evBtn = new EventButton(button, scrType);
		_buttons.add(evBtn);
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		_wasTouchDown = true;
		for (EventButton evBtn : _buttons) {
			if (this.isButtonUnderPoint(evBtn.btn)) {
				evBtn.btn.setClickedMode();
			} else {
				evBtn.btn.setNormalMode();
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (!_wasTouchDown) { return false; }
		_wasTouchDown = false;
		for (EventButton evBtn : _buttons) {
			evBtn.btn.setNormalMode();
			if (this.isButtonUnderPoint(evBtn.btn)) {
				_game.setScreen(ScreenControl.getScreen(evBtn.type));
				if (evBtn.btn.index == GameStrategy.RUN_GAME || 
						evBtn.btn.index == GameStrategy.SHOOT_GAME) {
					((GameScreen)_game.getScreen()).play(evBtn.btn.index);
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		for (EventButton evBtn : _buttons) {
			if (this.isButtonUnderPoint(evBtn.btn)) {
				evBtn.btn.setClickedMode();
			} else {
				evBtn.btn.setNormalMode();
			}
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
	
	final class EventButton {
		Button btn;
		int type;
		
		public EventButton(Button button, int scrType) {
			btn = button;
			type = scrType;
		}
	}
}
