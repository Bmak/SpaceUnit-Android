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
import com.glowman.spaceunit.core.AnimatedSprite;
import com.glowman.spaceunit.core.FPSViewer;
import com.glowman.spaceunit.core.ScreenControl;
import com.glowman.spaceunit.core.CameraHelper.ViewportMode;
import com.glowman.spaceunit.core.ScreenControl;
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
	private GameStrategy _gameStrategy;

	private final SpriteBatch _drawer;
	private OrthographicCamera _camera;

	public GameScreen(Game game, OrthographicCamera camera)
	{
		_game = game;
		_camera = camera;
		_drawer = new SpriteBatch();
		_drawer.setProjectionMatrix(_camera.combined);
		
		this.createShip();
	}
	
	public void play(int gameType) {
		//TODO reset game, update game strategy
		_gameType = gameType;
		Log.d("hz", "game type : " + _gameType);
		_gameStrategy = GameStrategyFactory.createStrategy(_ship, _gameType);
		Gdx.input.setInputProcessor(new GameTouchListener(_camera, _gameStrategy));
	}


	@Override
	public void render(float delta) {
		_timer++;
		if (_timer > TIMER_MAX)
		{
			_game.setScreen(ScreenControl.getScreen(ScreenControl.MAIN));
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
		_timer = 0;
	}
	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
		this.clear();
		//TODO остановка/пауза всех просчетов, которые могут выполнятся
	}
	@Override public void pause() {}
	@Override public void resume() {}
	@Override public void dispose() {}

	private void createShip() {
		if (_ship != null) { Log.e("hz", "ship already exists!"); }

		_ship = new Ship(new Sprite(Assets.ship), 10);
		_ship.setGeneralSpeed(SpeedFactory.getSpeed(_ship, _gameType));
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
