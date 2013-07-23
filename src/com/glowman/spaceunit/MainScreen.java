package com.glowman.spaceunit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import android.util.Log;

import com.glowman.spaceunit.game.behavior.AsteroidsBehavior;
import com.glowman.spaceunit.game.core.Button;


public class MainScreen implements Screen {
	
	float MENU_WIDTH = Gdx.graphics.getWidth();
	float MENU_HEIGHT = Gdx.graphics.getHeight();
	
	private Game _game;

	SpriteBatch _spriteBatch;

	private Button _playBtnRun;
	private Button _playBtnShoot;
	private Sprite _bkg;
	private Vector3 _touchPoint;
	
	private AsteroidsBehavior _behavior;


	public MainScreen(Game game) {
		_game = game;


		_touchPoint = new Vector3();
		
		_bkg = Assets.bkg2;
		_playBtnRun = new Button(Assets.getPlayRunRegion(1), Assets.getPlayRunRegion(2));
		_playBtnShoot = new Button(Assets.getPlayShootRegion(1), Assets.getPlayShootRegion(2));

		_spriteBatch = new SpriteBatch();
		
		_behavior = new AsteroidsBehavior(15, _spriteBatch);

		Gdx.input.setInputProcessor(new MenuTouchListener(_game, _playBtnRun, _playBtnShoot));
		
		//why this shit if still working without?
		//spriteBatch.setProjectionMatrix(menuCam.combined);

		Log.d("SIZE", "size: " + MENU_WIDTH + " / " + MENU_HEIGHT);
		
	}

	@Override
	public void resize(int width, int height) {
		MENU_WIDTH = (float) width;
		MENU_HEIGHT = (float) height;
		
		Log.d("RESIZE", "REsize: " + MENU_WIDTH + " / " + MENU_HEIGHT);
		

		_playBtnRun.setScale(0.75f);
		_playBtnRun.setX(MENU_WIDTH/2 - _playBtnRun.getView().getWidth() - 10);
		_playBtnRun.setY((MENU_HEIGHT - _playBtnRun.getView().getHeight())/2);
		
		_playBtnShoot.setScale(0.75f);
		_playBtnShoot.setX(MENU_WIDTH/2 + 10);
		_playBtnShoot.setY((MENU_HEIGHT - _playBtnShoot.getView().getHeight())/2);
		
		
		//TODO this shit cuz wrong picture
		//_bkg.setRotation(90);
		_bkg.setSize(MENU_WIDTH, MENU_HEIGHT);
	}

	@Override
	public void hide() {
		this.clear();
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float deltaTime) {
		this.clear();
		_spriteBatch.begin();
		
		_bkg.draw(_spriteBatch);
		_behavior.tick(deltaTime);
		_playBtnRun.draw(_spriteBatch);
		_playBtnShoot.draw(_spriteBatch);
		
		_spriteBatch.end();
		
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
		Log.d("hz", "dispose!!");
		this.clear();
	}

	private void clear() {
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}


}
