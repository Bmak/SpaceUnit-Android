package com.glowman.spaceunit;

import android.os.Vibrator;

/**
 *
 */
public class AppVibrator {
	private static Vibrator _instance = null;

	public static void init(Vibrator instance) {
		_instance = instance;
	}

	public static Vibrator getInstance() {
		if (_instance == null) {
			throw new Error("not inited yet");
		}
		return _instance;
	}
}
