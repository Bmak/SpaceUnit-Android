package com.glowman.spaceunit;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.glowman.spaceunit.core.GPGSActivity;
import com.glowman.spaceunit.data.GooglePlayData;
import com.google.android.gms.games.Player;

public class MainAndroid extends GPGSActivity {
	// request codes we use when invoking an external activity
	public final int RC_RESOLVE = 5000, RC_UNUSED = 5001;
	
	// tag for debug logging
    final boolean ENABLE_DEBUG = false;
    final String TAG = "ProBIGI";
	
	@Override
	public void onCreate (Bundle savedInstanceState) {
		enableDebugLog(ENABLE_DEBUG, TAG);
		super.onCreate(savedInstanceState);
		
		
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useWakelock = true;
		config.useGL20 = false;
		
		GooglePlayData.game = this;
		GooglePlayData.gameHelper = super.mHelper;
		GooglePlayData.gamesClient = super.getGamesClient();
		beginUserInit();
		
		initialize(new Main(), config);
	}
	
	public void beginUserInit() {
		try {
			super.beginUserInitiatedSignIn();
		} catch (Exception ex) {
			Log.d("Google Play hz", "beginUserInit have some faild!");
		}
	}
	
	@Override
	public void onSignInFailed() {
		Gdx.app.log("Google Play onSignInFailed", "Oh NOOO! ");
	}

	@Override
	public void onSignInSucceeded() {
		Gdx.app.log("Google Play onSignInSucceeded", "FUCK YEAH!");
		
		Player p = GooglePlayData.gamesClient.getCurrentPlayer();
		if (p != null) {
			Gdx.app.log("Google Play onSignInSucceeded", "Welcome " + p.getDisplayName());
		}
	}
	
}