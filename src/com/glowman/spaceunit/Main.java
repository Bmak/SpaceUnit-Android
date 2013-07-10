package com.glowman.spaceunit;

import com.glowman.android.framework.Screen;
import com.glowman.android.framework.impl.AndroidGame;


/**
 * мейн класс на Canvas
 * @author MAX
 */

public class Main extends AndroidGame{

	@Override
	public Screen getStartScreen() {
		return new MainScreen(this);
	}

}
