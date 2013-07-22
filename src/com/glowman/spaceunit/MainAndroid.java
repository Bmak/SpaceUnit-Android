package com.glowman.spaceunit;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

/**
 *
 */
public class MainAndroid extends AndroidApplication {

		@Override
		public void onCreate (Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
			config.useAccelerometer = false;
			config.useCompass = false;
			config.useWakelock = true;
			config.useGL20 = false;
			
			initialize(new Main(), config);
		}
}