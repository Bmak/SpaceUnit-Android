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
import com.glowman.spaceunit.core.AnimatedSprite;
import com.glowman.spaceunit.core.CameraHelper;
import com.glowman.spaceunit.core.FPSViewer;
import com.glowman.spaceunit.core.CameraHelper.ViewportMode;
import com.glowman.spaceunit.game.mapObject.Bullet;
import com.glowman.spaceunit.game.mapObject.Ship;
import com.glowman.spaceunit.game.mapObject.enemy.Enemy;
import com.glowman.spaceunit.game.strategy.GameStrategy;
import com.glowman.spaceunit.game.strategy.GameStrategyFactory;


public class GameScreen implements Screen {
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

	public GameScreen(Game game, int gameType)
	{
		_game = game;
		_gameCam = CameraHelper.createCamera2(ViewportMode.STRETCH_TO_SCREEN, Assets.VIRTUAL_WIDTH, Assets.VIRTUAL_HEIGHT, Assets.pixelDensity);
		_drawer = new SpriteBatch();
		_drawer.setProjectionMatrix(_gameCam.combined);
		
		this.createShip();
		Log.d("hz", "game type : " + gameType);
		_gameStrategy = GameStrategyFactory.createStrategy(_ship, gameType);
	}


	@Override
	public void render(float delta) {
		_timer++;
		if (_timer > TIMER_MAX)
		{
			_game.setScreen(new MainScreen(_game));
		}
		_gameStrategy.tick(delta);

		this.clear();
		_drawer.begin();

		this.drawHero();
		this.drawEnemies();
		this.drawBullets();
		this.drawAnimations();
		
		FPSViewer.draw(_drawer);
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

		_ship = new Ship(new Sprite(Assets.ship), 10);
		_ship.setGeneralSpeed(1f);
		_ship.setPosition(new Vector2(Assets.VIRTUAL_WIDTH / 2, Assets.VIRTUAL_HEIGHT / 2));
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

	private void drawBullets() {
		if (_gameStrategy.getBullets() != null) {
			for (Bullet bullet : _gameStrategy.getBullets()) {
				bullet.getView().draw(_drawer);
			}
		}
	}

	private void drawAnimations() {
		if (_gameStrategy.getAnimations() != null) {
			for (AnimatedSprite animation : _gameStrategy.getAnimations()) {
				animation.draw(_drawer);
			}
		}
	}

	private void clear() {
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}

}
