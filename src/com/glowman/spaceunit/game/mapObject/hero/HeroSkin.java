package com.glowman.spaceunit.game.mapObject.hero;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 */
abstract public class HeroSkin extends Sprite {
	public HeroSkin(){}
	public HeroSkin(TextureRegion textureRegion) {
		super(textureRegion);
	}

	abstract public void tick(float delta, boolean moving);

	abstract public boolean rotatable();
}
