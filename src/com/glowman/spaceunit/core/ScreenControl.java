package com.glowman.spaceunit.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.glowman.spaceunit.menu.MainScreen;
import com.glowman.spaceunit.achievements.AchievementsScreen;
import com.glowman.spaceunit.credits.CreditsScreen;
import com.glowman.spaceunit.game.GameScreen;
import com.glowman.spaceunit.hightscores.HighScoresScreen;

public class ScreenControl {
	
	public static final int MAIN = 0;
	public static final int GAME = 1;
	public static final int CREDITS = 2;
	public static final int HIGHSCORES = 3;
	public static final int ACHIEVEMENTS = 4;
	
	
	//private static MainScreen _mainScreen;
	//private static GameScreen _gameScreen;
	//private static HigthscoresScreen _hightscoresScreen;
	//private static CreditsScreen _creditsScreen;
	
	private static OrthographicCamera _camera;
	private static Game _game;
	
	private static final int LEN = 3;
	
	public static Screen[] Screens = new Screen[LEN];
	
	public static void init(Game game, OrthographicCamera camera) {
		_game = game;
		_camera = camera;
		for (int i = 0; i < LEN; i++) {
			Screens[i] = null;
		}
	}
	public static Screen getScreen(int type) {
		if (type > Screens.length) throw new Error("Undefine type");
		if (Screens[type] == null) {
			switch(type) {
				case MAIN:
					Screens[type] = new MainScreen(_game, _camera);
					break;
				case GAME:
					Screens[type] = new GameScreen(_game, _camera);
					break;
				case HIGHSCORES:
					//Screens[type] = new HighScoresScreen(_game, _camera);
					break;
				case ACHIEVEMENTS:
					//Screens[type] = new AchievementsScreen(_game, _camera);
					break;
				case CREDITS:
					Screens[type] = new CreditsScreen(_game, _camera);
					break;
			}
		}
		return Screens[type];
	}
}
