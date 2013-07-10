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

import java.util.List;
import java.util.logging.Logger;


public class MainScreen extends Screen {

	Circle button;
	Graphics g;
	int btnColor;

	public MainScreen(Game game) {
		super(game);

		g = game.getGraphics();

		button = new Circle(g.getWidth()/2, g.getHeight()/2, 50);
		btnColor = Color.DKGRAY;
	}

	@Override
	public void update(float deltaTime) {
		List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
		int len = touchEvents.size();

		for(int i = 0; i < len; i++) {
			Input.TouchEvent event = touchEvents.get(i);
			Log.d("hz", "event : " + event.type);

			if (OverlapTester.pointInCircle(button, new Vector2(event.x, event.y))) {
				if(event.type == Input.TouchEvent.TOUCH_DOWN) {
					btnColor = Color.RED;
				} else if(event.type == Input.TouchEvent.TOUCH_UP) {
					btnColor = Color.DKGRAY;
					g.clear(Color.BLACK);
					game.setScreen(new TestScreenOne(game));
				}
			} else {
				btnColor = Color.DKGRAY;
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		g.drawCirlce(button.center.x, button.center.y, button.radius, btnColor);

		g.drawText("push", (int)button.center.x, (int)button.center.y, 25, Color.BLACK);
	}

	@Override
	public void pause() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void resume() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void dispose() {

	}
}
