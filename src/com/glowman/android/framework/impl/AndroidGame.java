package com.glowman.android.framework.impl;

import com.glowman.android.framework.Audio;
import com.glowman.android.framework.FileIO;
import com.glowman.android.framework.Game;
import com.glowman.android.framework.Graphics;
import com.glowman.android.framework.Input;
import com.glowman.android.framework.Screen;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class AndroidGame extends Activity implements Game {
	AndroidFastRenderView renderView;
	Graphics graphics;
	Audio audio;
	Input input;
	FileIO fileIO;
	Screen screen;
	WakeLock wakeLock;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
		int frameBufferWidth = isLandscape ? 480 : 320;
		int frameBufferHeight = isLandscape ? 320 : 480;
		
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight, Config.RGB_565);
		float scaleX;
		float scaleY;
		if (VERSION.SDK_INT < 13) {
			scaleX = (float) frameBufferWidth / getWindowManager().getDefaultDisplay().getWidth();
			scaleY = (float) frameBufferHeight / getWindowManager().getDefaultDisplay().getHeight();
		} else {
			Point point = new Point(0, 0);
			getWindowManager().getDefaultDisplay().getSize(point);
			scaleX = (float) frameBufferWidth / point.x;
			scaleY = (float) frameBufferHeight / point.y;
		}
		
		Log.d("SCALE", "SCALE X " + scaleX);
		Log.d("SCALE", "SCALE Y " + scaleX);
		
		renderView = new AndroidFastRenderView(this, frameBuffer);
		
		graphics = new AndroidGraphics(getAssets(), frameBuffer);
		
		fileIO = new AndroidFileIO(getAssets());
		
		audio = new AndroidAudio(this);
		
		input = new AndroidInput(this, renderView, scaleX, scaleY);
		
		screen = getStartScreen();
		
		setContentView(renderView);
		
		//TODO устаревший вариант
		//PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		//wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		//wakeLock.acquire();
		screen.resume();
		renderView.resume();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		//wakeLock.release();
		renderView.pause();
		screen.pause();
		if (isFinishing()) {
			screen.dispose();
		}
	}
	
	@Override
	public Input getInput() { return input; }
	
	@Override
	public FileIO getFileIO() { return fileIO; }
	
	@Override
	public Graphics getGraphics() { return graphics; }
	
	@Override
	public Audio getAudio() { return audio; }
	
	@Override
	public void setScreen(Screen screen) {
		if (screen == null) {
			throw new IllegalArgumentException("Screen must not be null");
		}
		this.screen.pause();
		this.screen.dispose();
		screen.resume();
		screen.update(0);
		this.screen = screen;
	}
	
	public Screen getCurrentScreen() { return screen; }

	public Screen getStartScreen() {
		// TODO Auto-generated method stub
		// TODO for override
		return null;
	}
}
