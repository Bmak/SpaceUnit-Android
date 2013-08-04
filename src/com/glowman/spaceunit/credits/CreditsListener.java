package com.glowman.spaceunit.credits;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;
import com.glowman.spaceunit.CoordinatesTranslator;
import com.glowman.spaceunit.core.Button;
import com.glowman.spaceunit.core.ScreenControl;
import com.glowman.spaceunit.game.mapObject.MovingSpaceObject;
import com.glowman.spaceunit.menu.behavior.AsteroidsBehavior;

public class CreditsListener extends GestureDetector {
	
	private Game _game;
	public Boolean wasTouchDown = false;
	private Vector3 _touchPoint;
	private ArrayList<EventButton> _buttons;
	
	private ArrayList<AsteroidsBehavior> _behaviors;
	
	//TODO refact all listeners
	
	public CreditsListener(Game game, GestureListener listener) {
		super(listener);
		_game = game;
		_buttons = new ArrayList<EventButton>();
		_behaviors = new ArrayList<AsteroidsBehavior>();
	}
	
	public void addBehavior(AsteroidsBehavior behavior) {
		_behaviors.add(behavior);
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
			if (this.isViewUnderPoint(evBtn.btn.getView())) {
				evBtn.btn.setClickedMode();
			} else {
				evBtn.btn.setNormalMode();
			}
		}
		for (AsteroidsBehavior behavior : _behaviors) {
			for (MovingSpaceObject object : behavior.getSpaceObjects()) {
				if (this.isViewUnderPoint(object.getImage())) {
					behavior.setBlow(object);
				}
			}
		}
		
		return super.touchDown(x, y, pointer, button);
	}
	
	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		if (!wasTouchDown) { return false; }
		wasTouchDown = false;
		for (EventButton evBtn : _buttons) {
			evBtn.btn.setNormalMode();
			if (this.isViewUnderPoint(evBtn.btn.getView())) {
				_game.setScreen(ScreenControl.getScreen(ScreenControl.MAIN));
			}
		}
		return super.touchUp(x, y, pointer, button);
	}
	
	@Override
	public boolean touchDragged(float x, float y, int pointer) {
		if (!wasTouchDown) { return false; }
		for (EventButton evBtn : _buttons) {
			if (this.isViewUnderPoint(evBtn.btn.getView())) {
				evBtn.btn.setClickedMode();
			} else {
				evBtn.btn.setNormalMode();
			}
		}
		return super.touchDragged(x, y, pointer);
	}
	
	private boolean isViewUnderPoint(Sprite view)
	{
		_touchPoint = CoordinatesTranslator.toVirtualView(Gdx.input.getX(), Gdx.input.getY());

		if (view.getBoundingRectangle().contains(_touchPoint.x, _touchPoint.y)) {
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
