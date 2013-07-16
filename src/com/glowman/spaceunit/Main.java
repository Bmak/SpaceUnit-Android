package com.glowman.spaceunit;

import android.os.Bundle;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.android.AndroidApplication;


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
