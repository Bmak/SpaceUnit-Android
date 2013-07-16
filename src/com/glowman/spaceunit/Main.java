package com.glowman.spaceunit;

import com.badlogic.gdx.Game;


/**
 * мейн класс на Canvas
 * @author MAX
 */

public class Main extends Game{

	@Override
	public void create () {
		setScreen(new MainScreen(this));
	}

}
