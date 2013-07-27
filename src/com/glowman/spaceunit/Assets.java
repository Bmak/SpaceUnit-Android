package com.glowman.spaceunit;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static final float VIRTUAL_WIDTH = 30.0f;
	public static final float VIRTUAL_HEIGHT = 20.0f;
	
	private static TextureAtlas atlas;

	public static TextureRegion ship;
	public static TextureRegion bullet;

	public static TextureRegion bkg;

	public static TextureRegion asteroid;
	public static TextureRegion meteor;
	public static TextureRegion alienPassive;
	public static TextureRegion alienActive;
	public static TextureRegion minePassive;
	public static TextureRegion mineActive;

	public static Array<TextureAtlas.AtlasRegion> blowArray;
	
	public static TextureRegion gameFontRegion;
	public static String gameFontPath;
	public static BitmapFont gameFont;
	
	public static float playBtnWidth;
	public static float playBtnHeight;
	public static float simpleBtnWidth;
	public static float simpleBtnHeight;

	public static float pixelDensity;


	public static TextureRegion[] soImages;

	public static void load() {
		pixelDensity = calculatePixelDensity();
		atlas = new TextureAtlas(Gdx.files.internal("textures/textures.pack"));
		
		loadTextures();
		initialiseGeometries();
	}
	
	private static void loadTextures () {
		bkg = atlas.findRegion("bkg/bkg");

		ship = atlas.findRegion("unit/unit");
		bullet = atlas.findRegion("unit/bullet");

		blowArray = atlas.findRegions("blow/blow");

		asteroid = atlas.findRegion("enemies/asteroid");
		meteor = atlas.findRegion("enemies/meteor");

		alienPassive = atlas.findRegion("enemies/alien1");
		alienActive = atlas.findRegion("enemies/alien2");
		minePassive = atlas.findRegion("enemies/mine1");
		mineActive = atlas.findRegion("enemies/mine2");

		gameFontRegion = atlas.findRegion("fonts/font");
		gameFontPath = "fonts/font.fnt";
		gameFont = new BitmapFont(Gdx.files.internal(gameFontPath), gameFontRegion, false);
		
		soImages = new TextureRegion[2];
		soImages[0] = asteroid;
		soImages[1] = meteor;
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
	
	private static float calculatePixelDensity () {
		float physWidth = Gdx.graphics.getWidth();
		float physHeight = Gdx.graphics.getHeight();
		float physDiag = (float)Math.hypot(physWidth, physHeight);
		float virtualDiag = (float)Math.hypot(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		float density = physDiag / virtualDiag;
		Log.d("hz", "density: " + density);

		return density;
	}
	
	private static void initialiseGeometries () {
		playBtnWidth = toWidth(getPlayRunRegion(1));
		playBtnHeight = toHeight(getPlayRunRegion(1));
		simpleBtnWidth = toWidth(getSimpleBtnRegion(1));
		simpleBtnHeight = toHeight(getSimpleBtnRegion(1));
	}
	
	private static float toWidth (TextureRegion region) {
		return region.getRegionWidth() / pixelDensity;
	}

	private static float toHeight (TextureRegion region) {
		return region.getRegionHeight() / pixelDensity;
	}

}
