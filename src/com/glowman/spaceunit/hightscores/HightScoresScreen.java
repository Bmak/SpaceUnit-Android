package com.glowman.spaceunit.hightscores;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.glowman.spaceunit.core.ScreenControl;

public class HightScoresScreen implements Screen {

	private final Game _game;
	private final SpriteBatch _drawer;
	private OrthographicCamera _camera;
	
	//DEBUG
	private int _timer = 0;
	private final int TIMER_MAX = 200;
	
	public HightScoresScreen(Game game, OrthographicCamera camera)
	{
		_game = game;
		_camera = camera;
		_drawer = new SpriteBatch();
		_drawer.setProjectionMatrix(_camera.combined);
		
	}
	
	
	@Override
	public void render(float delta) {
		_timer++;
		if (_timer > TIMER_MAX)
		{
			_game.setScreen(ScreenControl.getScreen(ScreenControl.MAIN));
		}
		
		this.clear();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		_timer = 0;
	}

	@Override
	public void hide() {
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
