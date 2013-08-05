package com.glowman.spaceunit.achievements;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.core.Button;
import com.glowman.spaceunit.core.FPSViewer;
import com.glowman.spaceunit.core.ScreenControl;

public class AchievementsScreen implements Screen {
	
	private final Game _game;
	private final SpriteBatch _drawer;
	private OrthographicCamera _camera;
	
	private Sprite _bkg;
	private Button _backBtn;
	private Button _showAchievementsBtn;
	
	private AchievementsListener _listener;
	
	public AchievementsScreen(Game game, OrthographicCamera camera) {
		_game = game;
		_camera = camera;
		_drawer = new SpriteBatch();
		_drawer.setProjectionMatrix(_camera.combined);
		
		_bkg = new Sprite(Assets.bkg);
		_bkg.setSize(Assets.VIRTUAL_WIDTH, Assets.VIRTUAL_HEIGHT);
		
		_backBtn = new Button(Assets.getBackBtnRegin(1), Assets.getBackBtnRegin(2));
		_backBtn.setPosition(_backBtn.getWidth()*0.2f, _backBtn.getHeight()*0.2f);
		
		_showAchievementsBtn = new Button(Assets.getGoogleBtn(1), Assets.getGoogleBtn(2));
		_showAchievementsBtn.setPosition(Assets.VIRTUAL_WIDTH - _showAchievementsBtn.getWidth()*1.2f,
							_showAchievementsBtn.getHeight()*0.2f);
		
		_listener = new AchievementsListener(_game);
		_listener.addButton(_backBtn, ScreenControl.MAIN);
		_listener.addButton(_showAchievementsBtn, -1);
	}
	
	@Override
	public void render(float delta) {
		this.clear();
		
		_drawer.begin();
		
		_bkg.draw(_drawer, 0.5f);
		
		_backBtn.draw(_drawer);
		_showAchievementsBtn.draw(_drawer);
		
		FPSViewer.draw(_drawer);
		_drawer.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(_listener);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
		this.clear();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		this.clear();
	}

	private void clear() {
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}
}
