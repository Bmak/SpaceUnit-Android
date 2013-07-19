package com.glowman.spaceunit;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.MainScreen;


public class Main extends Game {
	
	FPSLogger _fps;
	
	Screen mainScreen;
	
	@Override
	public void create () {
		Assets.load();
		
		mainScreen = new MainScreen(this);
		
		setScreen(mainScreen);
		
		_fps = new FPSLogger();
	}
	
	@Override
	public void render() {
		super.render();
		_fps.log();
	}
	
	@Override
	public void dispose () {
		super.dispose();

		getScreen().dispose();
	}

}
