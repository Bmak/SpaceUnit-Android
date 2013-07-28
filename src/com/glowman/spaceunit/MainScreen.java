package com.glowman.spaceunit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import android.util.Log;

import com.glowman.spaceunit.game.behavior.AsteroidsBehavior;
import com.glowman.spaceunit.game.strategy.GameStrategy;
import com.glowman.spaceunit.core.Button;
import com.glowman.spaceunit.core.CameraHelper;
import com.glowman.spaceunit.core.ScreenControl;
import com.glowman.spaceunit.core.CameraHelper.ViewportMode;
import com.glowman.spaceunit.core.FPSViewer;
import com.glowman.spaceunit.core.TextButton;
import com.glowman.spaceunit.core.ScreenControl;


public class MainScreen implements Screen {
	
	private Game _game;

	private SpriteBatch _spriteBatch;
	
	private OrthographicCamera _camera;
	private Button _playBtnRun;
	private Button _playBtnShoot;
	private TextButton _hightscoresBtn;
	private TextButton _creditsBtn;
	private Sprite _bkg;
	
	private MenuTouchListener _listener;
	
	private AsteroidsBehavior _behavior;

	public MainScreen(Game game, OrthographicCamera camera) {
		_game = game;

		_camera = camera;
		_spriteBatch = new SpriteBatch();
		_spriteBatch.setProjectionMatrix(_camera.combined);
		
		createItems();
	}
	
	private void createItems() {
		_bkg = new Sprite(Assets.bkg);
		_playBtnRun = new Button(Assets.getPlayRunRegion(1), Assets.getPlayRunRegion(2));
		_playBtnRun.index = GameStrategy.RUN_GAME;
		_playBtnShoot = new Button(Assets.getPlayShootRegion(1), Assets.getPlayShootRegion(2));
		_playBtnShoot.index = GameStrategy.SHOOT_GAME;
		_hightscoresBtn = new TextButton(Assets.getSimpleBtnRegion(1), Assets.getSimpleBtnRegion(2), "HightScores");
		_creditsBtn = new TextButton(Assets.getSimpleBtnRegion(1), Assets.getSimpleBtnRegion(2), "Credits");
		
		_listener = new MenuTouchListener(_game);
		
		_behavior = new AsteroidsBehavior(15, _spriteBatch);
		
		initItems();
	}
	
	private void initItems() {
		_listener.addButton(_playBtnRun, ScreenControl.GAME);
		_listener.addButton(_playBtnShoot, ScreenControl.GAME);
		_listener.addButton(_hightscoresBtn, ScreenControl.HIGHTSCORES);
		_listener.addButton(_creditsBtn, ScreenControl.CREDITS);
		
		_bkg.setSize(Assets.VIRTUAL_WIDTH, Assets.VIRTUAL_HEIGHT);
		
		_playBtnRun.setSize(Assets.playBtnWidth, Assets.playBtnHeight);
		//_playBtnRun.setScale(0.9f);
		_playBtnRun.setX(Assets.VIRTUAL_WIDTH/2 - _playBtnRun.getWidth() - _playBtnRun.getWidth()/10);
		_playBtnRun.setY((Assets.VIRTUAL_HEIGHT - _playBtnRun.getHeight())/2 + _playBtnRun.getHeight()*0.3f);
		
		_playBtnShoot.setSize(Assets.playBtnWidth, Assets.playBtnHeight);
		//_playBtnShoot.setScale(0.9f);
		_playBtnShoot.setX(Assets.VIRTUAL_WIDTH/2 + _playBtnShoot.getWidth()/10);
		_playBtnShoot.setY((Assets.VIRTUAL_HEIGHT - _playBtnShoot.getHeight())/2 + _playBtnShoot.getHeight()*0.3f);
		
		_hightscoresBtn.setSize(Assets.simpleBtnWidth, Assets.simpleBtnHeight);
		//_hightscoresBtn.setScale(1.5f);
		_hightscoresBtn.setX((Assets.VIRTUAL_WIDTH - _hightscoresBtn.getWidth())/2);
		_hightscoresBtn.setY(_hightscoresBtn.getHeight()*1.5f);
		
		_creditsBtn.setSize(Assets.simpleBtnWidth, Assets.simpleBtnHeight);
		//_creditsBtn.setScale(1.5f);
		_creditsBtn.setX((Assets.VIRTUAL_WIDTH - _hightscoresBtn.getWidth())/2);
		_creditsBtn.setY(_creditsBtn.getHeight()*0.3f);
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void hide() {
		this.clear();
		Gdx.input.setInputProcessor(null);
	}

	float scale;
	@Override
	public void show() {
		Gdx.input.setInputProcessor(_listener);
	}

	@Override
	public void render(float deltaTime) {
		this.clear();
		
		_spriteBatch.begin();
		
		_bkg.draw(_spriteBatch);
		_behavior.tick(deltaTime);
		_playBtnRun.draw(_spriteBatch);
		_playBtnShoot.draw(_spriteBatch);
		_hightscoresBtn.draw(_spriteBatch);
		_creditsBtn.draw(_spriteBatch);
		
		FPSViewer.draw(_spriteBatch);
		_spriteBatch.end();
	}

	@Override
	public void pause() { }
	@Override
	public void resume() { }

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
