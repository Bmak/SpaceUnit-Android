package com.glowman.spaceunit;

import android.graphics.Color;

import android.util.Log;
import com.glowman.android.framework.Game;
import com.glowman.android.framework.Graphics;
import com.glowman.android.framework.Input;
import com.glowman.android.framework.Screen;
import com.glowman.android.framework.math.Circle;
import com.glowman.android.framework.math.OverlapTester;
import com.glowman.android.framework.math.Vector2;
import com.glowman.spaceunit.game.GameScreen;

import java.util.List;


public class MainScreen extends Screen {

	private final Circle _buttonModel;
	private final Graphics _g;

	private final int DEFAULT_BTN_COLOR = Color.DKGRAY;
	private final int TOUCH_BTN_COLOR = Color.RED;
	private final String BUTTON_TEXT = "push";

	public MainScreen(Game game) {
		super(game);

		_g = game.getGraphics();

		_buttonModel = new Circle(_g.getWidth()/2, _g.getHeight()/2, 50);

		this.drawButton();
	}

	@Override
	public void update(float deltaTime) {
		List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
		int len = touchEvents.size();

		for(int i = 0; i < len; i++) {
			Input.TouchEvent event = touchEvents.get(i);

			if (OverlapTester.pointInCircle(_buttonModel, new Vector2(event.x, event.y))) {
				if(event.type == Input.TouchEvent.TOUCH_DOWN) {
					this.updateButtonColor(TOUCH_BTN_COLOR);
				} else if(event.type == Input.TouchEvent.TOUCH_UP) {
					this.updateButtonColor(DEFAULT_BTN_COLOR);
					game.setScreen(new GameScreen(game));
				}
			} else {
				this.updateButtonColor(DEFAULT_BTN_COLOR);
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Log.d("hz", "present called");
	}

	@Override
	public void pause() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void resume() {
		this.drawButton();
	}

	@Override
	public void dispose() {
		_g.clear(Color.BLACK);
	}

	private void updateButtonColor(final int color) {
		this.drawButtonCircle(color);
	}

	private void drawButton() {
		this.drawButtonCircle(DEFAULT_BTN_COLOR);
		_g.drawText(BUTTON_TEXT, (int) _buttonModel.center.x, (int) _buttonModel.center.y, 25, Color.BLACK);
	}

	private void drawButtonCircle(final int circleColor)
	{
		_g.drawCirlce(_buttonModel.center.x, _buttonModel.center.y, _buttonModel.radius, circleColor);
	}

}
