package com.glowman.spaceunit;

import android.os.Build;
import android.util.Log;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.glowman.spaceunit.achievements.AchievementsConrol;
import com.glowman.spaceunit.core.FPSViewer;
import com.glowman.spaceunit.core.ScreenControl;
import com.glowman.spaceunit.data.GooglePlayData;

public class Loader implements Screen {

	private Game _game;
	
	private TextureAtlas _atlas;
	private Sprite _bkg;
	
	private OrthographicCamera _camera;
	private SpriteBatch _drawer;
	
	public Loader(Game game, OrthographicCamera camera) {
		_game = game;
		_camera = camera;
		
		Assets.initVirtualDimension();
		
		_atlas = new TextureAtlas(Gdx.files.internal("textures/loading.pack"));
		
		_bkg = new Sprite(_atlas.findRegion("loading"));
		
		_drawer = new SpriteBatch();
		_drawer.setProjectionMatrix(_camera.combined);
		
		_bkg.setSize(Assets.FULL_VIRTUAL_WIDTH, Assets.FULL_VIRTUAL_HEIGHT);
		_bkg.setPosition(Assets.FULL_X_OFFSET, Assets.FULL_Y_OFFSET);
	}
	
	@Override
	public void render(float delta) {
		this.clear();
		
		_drawer.begin();

		_bkg.draw(_drawer);

		FPSViewer.draw(_drawer);
		_drawer.end();
	}
	
	private void clear() {
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void show() {
		Assets.load();
		AchievementsConrol.init();
		
		_game.setScreen(ScreenControl.getScreen(ScreenControl.MAIN));
		
		SoundPlayer.playMusic(Assets.backMenuSound);
		
		Log.d("hz", "build product : " + Build.PRODUCT);
		if (!"google_sdk".equals( Build.PRODUCT ) && !GooglePlayData.gameHelper.isSignedIn()) {
			//GooglePlayData.gameHelper.showGreetAlert();
		}
	}

	@Override
	public void hide() {
		this.clear();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		this.clear();
	}
	
}
