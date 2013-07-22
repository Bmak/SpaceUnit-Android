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

	private Sprite _playBtnRun;
	private Sprite _playBtnShoot;
	private Sprite _bkg;
	private Vector3 _touchPoint;


	public MainScreen(Game game) {
		_game = game;

		_touchPoint = new Vector3();
		
		_bkg = Assets.bkg2;
		_playBtnRun = new Sprite(Assets.getPlayRunRegion(1));
		//_playBtnShoot = new Sprite(Assets.playBtnShoot1.getTexture());
		
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
		_playBtnRun.setX((MENU_WIDTH - _playBtnRun.getWidth())/2);
		_playBtnRun.setY((MENU_HEIGHT - _playBtnRun.getHeight())/2);
		
		//TODO refact this
		//Assets.playBtnRun1.setScale(1.5f);
		//Assets.playBtnRun2.setScale(1.5f);


//		Assets.playBtnRun1.setX((MENU_WIDTH - Assets.playBtnRun1.getWidth())/2);
//		Assets.playBtnRun1.setY((MENU_HEIGHT - Assets.playBtnRun1.getHeight())/2);
//		Assets.playBtnRun2.setX(Assets.playBtnRun1.getX());
//		Assets.playBtnRun2.setY(Assets.playBtnRun1.getY() + (Assets.playBtnRun1.getHeight() - Assets.playBtnRun2.getHeight()));
		
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
			_playBtnRun.setRegion(Assets.getPlayRunRegion(2));
		} else {
			_playBtnRun.setRegion(Assets.getPlayRunRegion(1));
		}
	}

	private boolean isButtonTouched(Sprite button)
	{
		boolean result = false;
		Vector3 touchPoint = new Vector3();
		if (Gdx.input.isTouched()) {
			touchPoint.x = Gdx.input.getX();
			touchPoint.y = Gdx.input.getY();

			if (button.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
				result = true;
			}
		}
		return result;
	}

}
