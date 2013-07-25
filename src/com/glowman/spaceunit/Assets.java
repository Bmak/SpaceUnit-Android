package com.glowman.spaceunit;

import android.R.string;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.glowman.spaceunit.game.core.CameraHelper;

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
	
	public static float playBtnWidth;
	public static float playBtnHeight;
	public static float asteroidWidth;
	public static float asteroidHeight;
	public static float shipWidth;
	public static float shipHeight;
	
	public static float pixelDensity;


	public static TextureRegion[] soImages;

	public static void load() {
		pixelDensity = calculatePixelDensity();
		Log.d("pixel", "density: " + pixelDensity);
		atlas = new TextureAtlas(Gdx.files.internal("textures/textures.pack"));
		
		loadTextures();
		initialiseGeometries();
	}
	
	private static void loadTextures () {
		bkg = atlas.findRegion("bkg/bkg");

		ship = atlas.findRegion("unit/unit");
		bullet = atlas.findRegion("unit/bullet");

		asteroid = atlas.findRegion("enemies/asteroid");
		meteor = atlas.findRegion("enemies/meteor");

		alienPassive = atlas.findRegion("enemies/alien1");
		alienActive = atlas.findRegion("enemies/alien2");
		minePassive = atlas.findRegion("enemies/mine1");
		mineActive = atlas.findRegion("enemies/mine2");


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
	
	private static float calculatePixelDensity () {
		FileHandle textureDir = Gdx.files.internal("textures");
		FileHandle[] availableDensities = textureDir.list();
		Log.d("CalculatePixelDensity ", "availableDensities: " + availableDensities);
		FloatArray densities = new FloatArray();
		//TODO why is 24?
		densities.add(24f);
		/*for (int i = 0; i < availableDensities.length; i++) {
			try {
				float density = Float.parseFloat(availableDensities[i].name());
				Log.d("CalculatePixelDensity ", "density: " + density);
				densities.add(density);
			} catch (NumberFormatException ex) {
				// Ignore anything non-numeric, such as ".svn" folders.
			}
		}*/
		
		densities.shrink(); // Remove empty slots to get rid of zeroes.
		densities.sort(); // Now the lowest density comes first.

		return CameraHelper.bestDensity(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, densities.items);
	}
	
	private static void initialiseGeometries () {
		playBtnWidth = toWidth(getPlayRunRegion(1));
		playBtnHeight = toHeight(getPlayRunRegion(1));
		
		//asteroidWidth = toWidth(asteroid);
		//asteroidHeight = toHeight(asteroid);
		
		//shipWidth = toWidth(ship);
		//shipHeight = toHeight(ship);
	}
	
	private static float toWidth (TextureRegion region) {
		return region.getRegionWidth() / pixelDensity;
	}

	private static float toHeight (TextureRegion region) {
		return region.getRegionHeight() / pixelDensity;
	}
}
