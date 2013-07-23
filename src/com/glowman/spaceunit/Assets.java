package com.glowman.spaceunit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	private static TextureAtlas atlas;

	public static TextureRegion bkg1;
	public static TextureRegion bkg2;

	public static TextureRegion asteroid;
	public static TextureRegion meteor;
	public static TextureRegion alienPassive;
	public static TextureRegion alienActive;
	public static TextureRegion minePassive;
	public static TextureRegion mineActive;


	public static TextureRegion[] soImages;

	public static void load() {
		atlas = new TextureAtlas(Gdx.files.internal("textures/textures.pack"));
		
		bkg1 = atlas.findRegion("bkg/bkg1");
		bkg2 = atlas.findRegion("bkg/bkg2");

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
}
