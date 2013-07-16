package com.glowman.spaceunit;

import android.graphics.Color;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;

import android.util.Log;
import com.glowman.spaceunit.game.GameScreen;

import java.util.List;


public class MainScreen implements Screen {

	//private Game _game;

	//private final Circle _buttonModel;
	//private final Graphics _g;

	private final int DEFAULT_BTN_COLOR = Color.DKGRAY;
	private final int TOUCH_BTN_COLOR = Color.RED;
	private final String BUTTON_TEXT = "push";

	public MainScreen(Game game) {
		//super(game);

		//_g = game.getGraphics();

		//_buttonModel = new Circle(_g.getWidth()/2, _g.getHeight()/2, 50);

		//this.drawButton();
	}

	@Override
	public void resize(int width, int height)
	{}

	@Override
	public void hide()
	{}

	@Override
	public void show() {}

	@Override
	public void render(float deltaTime) {
		Log.d("hz", "render!!!");
//		List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
//		int len = touchEvents.size();
//
//		for(int i = 0; i < len; i++) {
//			Input.TouchEvent event = touchEvents.get(i);
//
//			if (event.type == Input.TouchEvent.TOUCH_DRAGGED)
//			{
//				Log.d("hz", "touch dragged!");
//			}
//
//
//			if (OverlapTester.pointInCircle(_buttonModel, new Vector2(event.x, event.y))) {
//				if(event.type == Input.TouchEvent.TOUCH_DOWN) {
//					this.updateButtonColor(TOUCH_BTN_COLOR);
//				} else if(event.type == Input.TouchEvent.TOUCH_UP) {
//					this.updateButtonColor(DEFAULT_BTN_COLOR);
//					Log.d("hz", "try to change screen, TADASH!");
//					game.setScreen(new GameScreen(game));
//				}
//			} else {
//				this.updateButtonColor(DEFAULT_BTN_COLOR);
//			}
//		}
	}

//	@Override
//	public void present(float deltaTime) {
//	}

	@Override
	public void pause() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void resume() {
//		this.drawButton();
	}

	@Override
	public void dispose() {
		//_g.clear(Color.WHITE);
	}

//	private void updateButtonColor(final int color) {
//		this.drawButtonCircle(color);
//	}
//
//	private void drawButton() {
//		this.drawButtonCircle(DEFAULT_BTN_COLOR);
//		_g.drawText(BUTTON_TEXT, (int) _buttonModel.center.x, (int) _buttonModel.center.y, 25, Color.BLACK);
//	}
//
//	private void drawButtonCircle(final int circleColor)
//	{
//		_g.drawCirlce(_buttonModel.center.x, _buttonModel.center.y, _buttonModel.radius, circleColor);
//	}

}
