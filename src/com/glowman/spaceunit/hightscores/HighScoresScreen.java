package com.glowman.spaceunit.hightscores;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.Settings;
import com.glowman.spaceunit.core.Button;
import com.glowman.spaceunit.core.FPSViewer;
import com.glowman.spaceunit.core.ScreenControl;

public class HighScoresScreen implements Screen {

	private final Game _game;
	private final SpriteBatch _drawer;
	private OrthographicCamera _camera;
	
	private Button _backBtn;
	private BitmapFont _runners;
	private Vector2 _posRunners;
	private String TEXT_RUNNERS;
	private BitmapFont _killers;
	private Vector2 _posKillers;
	private String TEXT_KILLERS;
	
	private Sprite _highscores;
	
	private HighScoresListener _listener;
	
	
	public HighScoresScreen(Game game, OrthographicCamera camera)
	{
		_game = game;
		_camera = camera;
		_drawer = new SpriteBatch();
		_drawer.setProjectionMatrix(_camera.combined);
		
		_highscores = new Sprite(Assets.highscores);
		_highscores.setSize(Assets.VIRTUAL_WIDTH, Assets.VIRTUAL_HEIGHT);
		
		_backBtn = new Button(Assets.getBackBtnRegin(1), Assets.getBackBtnRegin(2));
		_backBtn.setSize(Assets.backBtnWidth/1.5f, Assets.backBtnHeight/1.5f);
		_backBtn.setX(_backBtn.getWidth()/5);
		_backBtn.setY(_backBtn.getHeight()/5);
		
		_listener = new HighScoresListener(_game);
		_listener.addButton(_backBtn, ScreenControl.MAIN);
		
		_runners = new BitmapFont(Gdx.files.internal(Assets.gameFontPath), Assets.gameFontRegion, false);
		_runners.setColor(Color.RED);
		_runners.setScale(1.5f/Assets.pixelDensity);
		_posRunners = new Vector2();
		
		_killers = new BitmapFont(Gdx.files.internal(Assets.gameFontPath), Assets.gameFontRegion, false);
		_killers.setColor(Color.RED);
		_killers.setScale(1.5f/Assets.pixelDensity);
		_posKillers = new Vector2();
	}
	
	
	@Override
	public void render(float delta) {
		this.clear();
		
		_drawer.begin();
		
		_highscores.draw(_drawer);
		_runners.drawMultiLine(_drawer, TEXT_RUNNERS, _posRunners.x, _posRunners.y);
		_killers.drawMultiLine(_drawer, TEXT_KILLERS, _posKillers.x, _posKillers.y);
		_backBtn.draw(_drawer);
		
		FPSViewer.draw(_drawer);
		_drawer.end();
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		//Settings.save();
		
		//TODO refact вычисление правильной координаты в таблице относительно текущих игровых данных
		TEXT_RUNNERS = "";
		for (int i = 0; i < Settings.run_highscores.length; i++) {
			TEXT_RUNNERS += (1 + i) + " - " + Settings.run_highscores[i] + "\n";
		}
		TextBounds runBounds = _runners.getMultiLineBounds(TEXT_RUNNERS);
		_posRunners.x = Assets.VIRTUAL_WIDTH/2 - runBounds.width*1.5f - runBounds.width*0.3f;
		_posRunners.y = (Assets.VIRTUAL_HEIGHT - runBounds.height*0.8f);
		
		
		TEXT_KILLERS = "";
		for (int i = 0; i < Settings.kill_highscores.length; i++) {
			TEXT_KILLERS += (1 + i) + " - " + Settings.kill_highscores[i] + "\n";
		}
		TextBounds killBounds = _killers.getMultiLineBounds(TEXT_KILLERS);
		_posKillers.x = Assets.VIRTUAL_WIDTH/2 + killBounds.width/2 + runBounds.width*0.3f;
		_posKillers.y = (Assets.VIRTUAL_HEIGHT - killBounds.height*0.8f);
		
		Gdx.input.setInputProcessor(_listener);
	}

	@Override
	public void hide() {
		this.clear();
		Gdx.input.setInputProcessor(null);
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
