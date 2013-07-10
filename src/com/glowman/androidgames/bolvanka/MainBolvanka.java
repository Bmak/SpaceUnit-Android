package com.glowman.androidgames.bolvanka;

import com.glowman.android.framework.Screen;
import com.glowman.android.framework.impl.AndroidGame;


/**
 * Пример создания болванки на системе отображения Canvas
 * @author MAX
 */

public class MainBolvanka extends AndroidGame {

	@Override
	public Screen getStartScreen() {
		return new MainScreen(this);
	}

}
