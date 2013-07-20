package com.glowman.spaceunit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import android.util.Log;



public class MainScreen implements Screen {
	
	float MENU_WIDTH = Gdx.graphics.getWidth();
	float MENU_HEIGHT = Gdx.graphics.getHeight();
	
	private Game _game;

	SpriteBatch spriteBatch;

	private Sprite _playBtn;
	private Sprite _bkg;
	private Vector3 _touchPoint;


	public MainScreen(Game game) {
		_game = game;

		_touchPoint = new Vector3();
		
		_bkg = Assets.bkg1;
		_playBtn = Assets.playBtnRun1;
		
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
		
		//TODO refact this
		Assets.playBtnRun1.setScale(1.5f);
		Assets.playBtnRun2.setScale(1.5f);
		
		Assets.playBtnRun1.setX((MENU_WIDTH - Assets.playBtnRun1.getWidth())/2);
		Assets.playBtnRun1.setY((MENU_HEIGHT - Assets.playBtnRun1.getHeight())/2);
		Assets.playBtnRun2.setX((MENU_WIDTH - Assets.playBtnRun2.getWidth())/2);
		Assets.playBtnRun2.setY((MENU_HEIGHT - Assets.playBtnRun2.getHeight())/2);
		
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
		_playBtn.draw(spriteBatch);
		
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
		_playBtn = Assets.playBtnRun1;
		if (Gdx.input.isTouched()) {
			_touchPoint.x = Gdx.input.getX();
			_touchPoint.y = Gdx.input.getY();

			if (_playBtn.getBoundingRectangle().contains(_touchPoint.x, _touchPoint.y)) {
				_playBtn = Assets.playBtnRun2;
			}
		}
	}

}
