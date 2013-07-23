package com.glowman.spaceunit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	private static TextureAtlas atlas;
	
	public static Sprite bkg1;
	public static Sprite bkg2;
	public static Sprite playBtnRun1;
	public static Sprite playBtnRun2;
	public static Sprite playBtnShoot1;
	public static Sprite playBtnShoot2;
	public static Animation playBtnShootAnim;
	
	public static Sprite asteroid;
	public static Sprite meteor;
	
	public static void load() {
		atlas = new TextureAtlas(Gdx.files.internal("textures/textures.pack"));
		
		bkg1 = new Sprite(atlas.findRegion("bkg/bkg1"));
		bkg2 = new Sprite(atlas.findRegion("bkg/bkg2"));
		playBtnRun1 = new Sprite(atlas.findRegion("buttons/PlayBtnRun", 1));
		playBtnRun2 = new Sprite(atlas.findRegion("buttons/PlayBtnRun", 2));
		playBtnShoot1  = new Sprite(atlas.findRegion("buttons/PlayBtnShoot", 1));
		playBtnShoot2  = new Sprite(atlas.findRegion("buttons/PlayBtnShoot", 2));
		
		playBtnShootAnim = new Animation(0.2f, Assets.playBtnShoot1, Assets.playBtnShoot2);
		
		
		asteroid = new Sprite(atlas.findRegion("enemies/asteroid"));
		meteor = new Sprite(atlas.findRegion("enemies/meteor"));
	}

	public static TextureRegion getPlayRunRegion(int index) {
		return atlas.findRegion("buttons/PlayBtnRun", index);
	}
	
	public static TextureRegion getPlayShootRegion(int index) {
		return atlas.findRegion("buttons/PlayBtnShoot", index);
	}
}
