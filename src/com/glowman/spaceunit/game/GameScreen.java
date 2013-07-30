package com.glowman.spaceunit.game;

import android.util.Log;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.core.AnimatedSprite;
import com.glowman.spaceunit.core.FPSViewer;
import com.glowman.spaceunit.core.ScreenControl;
import com.glowman.spaceunit.game.balance.SpeedCollector;
import com.glowman.spaceunit.game.mapObject.Bullet;
import com.glowman.spaceunit.game.mapObject.Ship;
import com.glowman.spaceunit.game.mapObject.enemy.Enemy;
import com.glowman.spaceunit.game.strategy.GameStrategy;
import com.glowman.spaceunit.game.strategy.GameStrategyFactory;


public class GameScreen implements Screen {

	private final Game _game;

	private int _gameType;
	private Ship _ship;
	private GameStrategy _gameStrategy;

	private final SpriteBatch _drawer;
	private OrthographicCamera _camera;
	private GameTouchListener _gameTouchListener;

	private BitmapFont _font;

	public GameScreen(Game game, OrthographicCamera camera)
	{
		_game = game;
		_camera = camera;
		_drawer = new SpriteBatch();
		_drawer.setProjectionMatrix(_camera.combined);
		_gameTouchListener = new GameTouchListener(_game, _camera);
		
	}
	
	public void play(int gameType) {
		_gameType = gameType;
		Log.d("hz", "game type : " + _gameType);
		this.createShip();
		_gameStrategy = GameStrategyFactory.createStrategy(_ship, _gameType);
		_gameStrategy.startGame();
		_gameTouchListener.init(_gameStrategy);
		Gdx.input.setInputProcessor(_gameTouchListener);
	}


	@Override
	public void render(float delta) {
		this.clear();
		_gameStrategy.tick(delta);

		if (_gameStrategy.isGameOver()) {
			_gameTouchListener.gameOver();
		}

		_drawer.begin();

		this.drawHero();
		this.drawEnemies();
		this.drawBullets();
		this.drawAnimations();

		if (_gameStrategy.isGameOver()) { this.drawGameOver(); }

		FPSViewer.draw(_drawer);
		_drawer.end();
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void show() {
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
		_ship.setGeneralSpeed(SpeedCollector.getHeroSpeed(_gameType));
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

	private void drawGameOver() {
		if (_font == null) {
			_font = new BitmapFont(Gdx.files.internal(Assets.gameFontPath), Assets.gameFontRegion, false);
			_font.setColor(Color.RED);
			_font.setScale(1f / Assets.pixelDensity);
		}
		BitmapFont.TextBounds bounds = _font.getBounds("Game Over");
		_font.draw(_drawer, "Game Over", Assets.VIRTUAL_WIDTH/2, Assets.VIRTUAL_HEIGHT/2);
	}

	private void clear() {
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}

}
