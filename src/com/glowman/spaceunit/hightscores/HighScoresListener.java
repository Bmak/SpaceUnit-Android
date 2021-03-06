package com.glowman.spaceunit.hightscores;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.glowman.spaceunit.CoordinatesTranslator;
import com.glowman.spaceunit.core.Button;
import com.glowman.spaceunit.core.ScreenControl;
import com.glowman.spaceunit.data.GooglePlayData;

public class HighScoresListener extends InputAdapter {
	
	private Game _game;
	public Boolean wasTouchDown = false;
	private Vector3 _touchPoint;
	private ArrayList<EventButton> _buttons;
	
	//TODO refact all listeners
	
	public HighScoresListener(Game game) {
		super();
		_game = game;
		_buttons = new ArrayList<EventButton>();
	}

	public void addButton(Button button, int scrType) {
		EventButton evBtn = new EventButton(button, scrType);
		_buttons.add(evBtn);
	}
	
	public void clearButtons() {
		_buttons.clear();
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		wasTouchDown = true;
		for (EventButton evBtn : _buttons) {
			if (this.isButtonUnderPoint(evBtn.btn)) {
				evBtn.btn.setClickedMode();
			} else {
				evBtn.btn.setNormalMode();
			}
		}
		return super.touchDown(x, y, pointer, button);
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (!wasTouchDown) { return false; }
		wasTouchDown = false;
		for (EventButton evBtn : _buttons) {
			evBtn.btn.setNormalMode();
			if (this.isButtonUnderPoint(evBtn.btn)) {
				if (evBtn.type == -1) {
					GooglePlayData.showAllLeaderboards();
				} else {
					_game.setScreen(ScreenControl.getScreen(evBtn.type));
				}
			}
		}
		return super.touchUp(screenX, screenY, pointer, button);
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (!wasTouchDown) { return false; }
		for (EventButton evBtn : _buttons) {
			if (this.isButtonUnderPoint(evBtn.btn)) {
				evBtn.btn.setClickedMode();
			} else {
				evBtn.btn.setNormalMode();
			}
		}
		return super.touchDragged(screenX, screenY, pointer);
	}
	
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
