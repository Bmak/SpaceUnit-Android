package com.glowman.spaceunit.game;

import android.graphics.Color;

import android.util.Log;
import com.glowman.android.framework.Graphics;
import com.glowman.android.framework.Game;
import com.glowman.android.framework.Pixmap;
import com.glowman.android.framework.Screen;
import com.glowman.android.framework.math.Vector2;
import com.glowman.spaceunit.game.mapObject.MapObjectImagesENUM;
import com.glowman.spaceunit.game.mapObject.Ship;
import com.glowman.spaceunit.game.strategy.GameRunStrategy;
import com.glowman.spaceunit.game.strategy.GameStrategy;

import com.glowman.spaceunit.game.mapObject.Enemy;

public class GameScreen extends Screen {

	public static final int SHOOT_GAME = 0;
	public static final int RUN_GAME = 1;

	public static final int MAX_TIME_ENEMY_RESPAWN = 3;

	private Ship _ship;
	private GameStrategy _gameStrategy;
	private int _gameType;
	private Graphics _graphics;

	public GameScreen(Game game) {
		super(game);
		_graphics = game.getGraphics();
		this.createShip();

		_gameStrategy = new GameRunStrategy(_graphics, _ship);

	}

	private void createShip() {
		if (_ship != null) { Log.e("hz", "ship already exists!"); }

		Pixmap pixMap = _graphics.newPixmap(MapObjectImagesENUM.HERO_SHIP);
		_ship = new Ship(pixMap, new Vector2(_graphics.getWidth(), _graphics.getHeight()), 10);
		_ship.setGeneralSpeed(2);
		_ship.setPosition(new Vector2(_graphics.getWidth() / 2, _graphics.getHeight() / 2));
	}

	@Override
	public void update(float deltaTime) {
		_gameStrategy.update();
//		 List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
//		 game.getInput().getKeyEvents();
//		 int len = touchEvents.size();
//
//		 for(int i = 0; i < len; i++) {
//			 Input.TouchEvent event = touchEvents.get(i);
//			 touchPoint.set(event.x,event.y);
//
//			 if (OverlapTester.pointInRectangle(backBtn, touchPoint)) {
//				 if(event.type == Input.TouchEvent.TOUCH_DOWN) {
//					btnColor = Color.YELLOW;
//				 } else if(event.type == Input.TouchEvent.TOUCH_UP) {
//					btnColor = Color.GREEN;
//					g.clear(Color.BLACK);
//					game.setScreen(new MainScreen(game));
//				 }
//			 } else {
//				btnColor = Color.GREEN;
//			 }
//		 }
	}

	@Override
	public void present(float deltaTime) {
		_graphics.clear(Color.GREEN);
		this.drawHero();
		this.drawEnemies();
	}

	@Override
	public void pause() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void resume() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void dispose() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	private void drawHero() {
		_graphics.drawPixmap(_ship.getImage(), (int)_ship.getPosition().x, (int)_ship.getPosition().y);
	}

	private void drawEnemies() {
		if (_gameStrategy.getEnemies() != null)
		{
			for (Enemy enemy : _gameStrategy.getEnemies())
			{
				this.drawEnemy(enemy);
			}
		}
		if (_gameStrategy.getDeadEnemies() != null)
		{
			for (Enemy enemy : _gameStrategy.getDeadEnemies())
			{
				this.drawEnemy(enemy);
			}
		}
	}

	private void drawEnemy(Enemy enemy) {
		_graphics.drawPixmap(enemy.getImage(), (int)enemy.getPosition().x, (int)enemy.getPosition().y);

	}

}
