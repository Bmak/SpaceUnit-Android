package com.glowman.spaceunit.game.core;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.glowman.spaceunit.MainScreen;
import com.glowman.spaceunit.game.GameScreen;

public class ScreenControl {
	
	public static final int MAIN = 0;
	public static final int GAME = 1;
	public static final int HIGHTSCORES = 2;
	public static final int CREDITS = 3;
	
	//private static MainScreen _mainScreen;
	//private static GameScreen _gameScreen;
	//private static HigthscoresScreen _hightscoresScreen;
	//private static CreditsScreen _creditsScreen;
	
	private static OrthographicCamera _camera;
	private static Game _game;
	
	
	public static Screen[] Screens = new Screen[4];
	
	public static void init(Game game, OrthographicCamera camera) {
		_game = game;
		_camera = camera;
		for (int i = 0; i < Screens.length; i++) {
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
				//..
			}
		}
		return Screens[type];
	}
}
