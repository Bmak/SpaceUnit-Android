package com.glowman.spaceunit;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static final float VIRTUAL_WIDTH = 960.0f;
	public static final float VIRTUAL_HEIGHT = 540.0f;

	public static float FULL_VIRTUAL_WIDTH;
	public static float FULL_VIRTUAL_HEIGHT;
	public static float FULL_X_OFFSET;
	public static float FULL_Y_OFFSET;

	public static final float GAME_INTERFACE_ALPHA = .6f;
	public static final float GAME_BKG_ALPHA = .3f;
	
	private static TextureAtlas atlas;
	
	public static TextureRegion ship;
	public static TextureRegion bullet;

	public static TextureRegion blueCircle;

	public static TextureRegion bkg;
	public static TextureRegion credits;
	public static TextureRegion title;
	public static TextureRegion highscores;

	public static TextureRegion asteroid;
	public static TextureRegion meteor;
	public static TextureRegion alienPassive;
	public static TextureRegion alienActive;
	public static TextureRegion minePassive;
	public static TextureRegion mineActive;

	public static TextureRegion joyPadBkg;
	public static TextureRegion joyPad;

	public static Array<TextureAtlas.AtlasRegion> blowArray;
	
	public static TextureRegion gameFontRegion;
	public static String gameFontPath;
	public static BitmapFont gameFont;
	
	//Sounds
	public static Sound shotSound;


	public static TextureRegion[] soImages;

	public static void load() {
		initVirtualDimension();

		atlas = new TextureAtlas(Gdx.files.internal("textures/textures.pack"));
		
		loadTextures();

	}

	private static void initVirtualDimension() {
		float coef = Math.max(VIRTUAL_WIDTH / Gdx.graphics.getWidth(), VIRTUAL_HEIGHT / Gdx.graphics.getHeight());
		FULL_VIRTUAL_WIDTH = Gdx.graphics.getWidth() * coef;
		FULL_VIRTUAL_HEIGHT = Gdx.graphics.getHeight() * coef;
		FULL_X_OFFSET = - (int) ((FULL_VIRTUAL_WIDTH - VIRTUAL_WIDTH) / 2);
		FULL_Y_OFFSET = - (int) ((FULL_VIRTUAL_HEIGHT - VIRTUAL_HEIGHT) / 2);

	}
	
	private static void loadTextures () {
		bkg = atlas.findRegion("bkg/bkg");
		credits = atlas.findRegion("screens/credits");
		title = atlas.findRegion("screens/title");
		//highscores = atlas.findRegion("screens/highscores");

		ship = atlas.findRegion("unit/unit");
		bullet = atlas.findRegion("unit/bullet");

		blowArray = atlas.findRegions("blow/blow");
		blueCircle = atlas.findRegion("blow/circle");

		asteroid = atlas.findRegion("enemies/asteroid");
		meteor = atlas.findRegion("enemies/meteor");

		alienPassive = atlas.findRegion("enemies/alien1");
		alienActive = atlas.findRegion("enemies/alien2");
		minePassive = atlas.findRegion("enemies/mine1");
		mineActive = atlas.findRegion("enemies/mine2");

		joyPad = atlas.findRegion("buttons/joyPad");
		joyPadBkg = atlas.findRegion("buttons/joyPadBkg");

		gameFontRegion = atlas.findRegion("fonts/font");
		gameFontPath = "fonts/font.fnt";
		gameFont = new BitmapFont(Gdx.files.internal(gameFontPath), gameFontRegion, false);
		
		soImages = new TextureRegion[2];
		soImages[0] = asteroid;
		soImages[1] = meteor;

		shotSound = Gdx.audio.newSound(Gdx.files.internal("sounds/shot.wav"));
	}
	
	public static void dispose() {
		atlas.dispose();
	}
	
	public static TextureRegion getPlayRunRegion(int index) {
		return atlas.findRegion("buttons/PlayBtnRun", index);
	}
	
	public static TextureRegion getPlayShootRegion(int index) {
		return atlas.findRegion("buttons/PlayBtnShoot", index);
	}
	
	public static TextureRegion getSimpleBtnRegion(int index) {
		return atlas.findRegion("buttons/SimpleBtn", index);
	}
	
	public static TextureRegion getBackBtnRegin(int index) {
		return atlas.findRegion("buttons/BackBtn", index);
	}
	
	public static TextureRegion getGoogleBtn(int index) {
		return atlas.findRegion("buttons/GoogleLedBtn", index);
	}

	public static TextureRegion getPauseBtn(int index) {
		return atlas.findRegion("buttons/PauseBtn", index);
	}
	
	private static float calculatePixelDensity () {
		float physWidth = Gdx.graphics.getWidth();
		float physHeight = Gdx.graphics.getHeight();
		float physDiag = (float)Math.hypot(physWidth, physHeight);
		float virtualDiag = (float)Math.hypot(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		float density = physDiag / virtualDiag;
		Log.d("hz", "density: " + density);
		
		density = 1;
		
		return density;
	}
	
}
