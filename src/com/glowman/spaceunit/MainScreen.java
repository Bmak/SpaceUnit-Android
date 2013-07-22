package com.glowman.spaceunit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import android.util.Log;
import com.glowman.spaceunit.game.core.Button;


public class MainScreen implements Screen {
	
	float MENU_WIDTH = Gdx.graphics.getWidth();
	float MENU_HEIGHT = Gdx.graphics.getHeight();
	
	private Game _game;

	SpriteBatch spriteBatch;

	private Button _playBtnRun;
	private Sprite _playBtnShoot;
	private Sprite _bkg;
	private Vector3 _touchPoint;


	public MainScreen(Game game) {
		_game = game;

		_touchPoint = new Vector3();
		
		_bkg = Assets.bkg2;
		_playBtnRun = new Button(Assets.getPlayRunRegion(1), Assets.getPlayRunRegion(2));

		spriteBatch = new SpriteBatch();

		//why this shit if still working without?
		//spriteBatch.setProjectionMatrix(menuCam.combined);

		Log.d("SIZE", "size: " + MENU_WIDTH + " / " + MENU_HEIGHT);
		
	}

	@Override
	public void resize(int width, int height) {
		MENU_WIDTH = (float) width;
		MENU_HEIGHT = (float) height;
		
		Log.d("RESIZE", "REsize: " + MENU_WIDTH + " / " + MENU_HEIGHT);

		_playBtnRun.setScale(0.5f);
		_playBtnRun.setX((MENU_WIDTH - _playBtnRun.getView().getWidth())/2);
		_playBtnRun.setY((MENU_HEIGHT - _playBtnRun.getView().getHeight())/2);
		
		//TODO this shit cuz wrong picture
		//_bkg.setRotation(90);
		_bkg.setSize(MENU_WIDTH, MENU_HEIGHT);
	}

	@Override
	public void hide() {}

	@Override
	public void show() {}

	@Override
	public void render(float deltaTime) {
		this.touchesProcessing();

		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		spriteBatch.begin();
		
		_bkg.draw(spriteBatch);
		_playBtnRun.draw(spriteBatch);
		
		spriteBatch.end();
		
	}


	@Override
	public void pause() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

	private void touchesProcessing()
	{
		if (this.isButtonTouched(_playBtnRun)) {
			_playBtnRun.setClickedMode();
		} else {
			_playBtnRun.setNormalMode();
		}
	}

	private boolean isButtonTouched(Button button)
	{
		boolean result = false;
		Vector3 touchPoint = new Vector3();
		if (Gdx.input.isTouched()) {
			touchPoint.x = Gdx.input.getX();
			touchPoint.y = Gdx.input.getY();

			if (button.getView().getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
				result = true;
			}
		}
		return result;
	}

}
