package com.glowman.spaceunit.game;

import android.util.Log;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.MainScreen;
import com.glowman.spaceunit.game.core.CameraHelper;
import com.glowman.spaceunit.game.core.CameraHelper.ViewportMode;
import com.glowman.spaceunit.game.mapObject.Ship;
import com.glowman.spaceunit.game.mapObject.Enemy;
import com.glowman.spaceunit.game.strategy.GameRunStrategy;
import com.glowman.spaceunit.game.strategy.GameStrategy;


public class GameScreen implements Screen {
	public static final int SHOOT_GAME = 0;
	public static final int RUN_GAME = 1;

	public static final int MAX_TIME_ENEMY_RESPAWN = 3;


	//DEBUG
	private int _timer = 0;
	private final int TIMER_MAX = 1000;

	private final Game _game;

	private int _gameType;
	private Ship _ship;
	private final GameStrategy _gameStrategy;

	private final SpriteBatch _drawer;
	private OrthographicCamera _gameCam;
	private Vector2 _screenSize;

	public GameScreen(Game game)
	{
		_game = game;
		_gameCam = CameraHelper.createCamera2(ViewportMode.STRETCH_TO_SCREEN, Assets.VIRTUAL_WIDTH, Assets.VIRTUAL_HEIGHT, Assets.pixelDensity);
		_drawer = new SpriteBatch();
		_drawer.setProjectionMatrix(_gameCam.combined);
		
		_screenSize = new Vector2(Assets.VIRTUAL_WIDTH, Assets.VIRTUAL_HEIGHT);
		this.createShip();
		_gameStrategy = new GameRunStrategy(_ship, _screenSize);
	}


	@Override
	public void render(float delta) {
		_timer++;
		if (_timer > TIMER_MAX)
		{
			_game.setScreen(new MainScreen(_game));
		}
		_gameStrategy.update();
		_ship.tick(delta);

		this.clear();
		_drawer.begin();

		this.drawHero();
		this.drawEnemies();

		_drawer.end();
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(new GameTouchListener(_gameCam, _gameStrategy));
		_timer = 0;
	}
	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
		this.clear();
	}
	@Override public void pause() {}
	@Override public void resume() {}
	@Override public void dispose() {}

	private void createShip() {
		if (_ship != null) { Log.e("hz", "ship already exists!"); }

		_ship = new Ship(new Sprite(Assets.ship), _screenSize, 10);
		_ship.setGeneralSpeed(1);
		//_ship.setPosition(new Vector2(_screenSize.x / 2, _screenSize.y / 2));
	}

	private void drawHero() {
		_ship.getImage().draw(_drawer);
	}

	private void drawEnemies() {
		if (_gameStrategy.getEnemies() != null)
		{
			for (Enemy enemy : _gameStrategy.getEnemies())
			{
				enemy.getImage().draw(_drawer);
			}
		}
		if (_gameStrategy.getDeadEnemies() != null)
		{
			for (Enemy enemy : _gameStrategy.getDeadEnemies())
			{
				enemy.getImage().draw(_drawer);
			}
		}
	}

	private void clear() {
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}

}
