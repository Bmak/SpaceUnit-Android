package com.glowman.spaceunit;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;


/**
 * мейн класс на Canvas
 * @author MAX
 */

public class Main extends AndroidApplication{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.initialize(new MainScreen(), true);
		  return new MainScreen(this);
	}

}
