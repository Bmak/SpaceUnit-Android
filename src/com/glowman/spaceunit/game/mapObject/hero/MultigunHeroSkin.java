package com.glowman.spaceunit.game.mapObject.hero;

import com.glowman.spaceunit.Assets;

/**
 *
 */
public class MultigunHeroSkin extends HeroSkin {
	public MultigunHeroSkin() {
		super(Assets.superShip);
	}

	@Override
	public boolean rotatable() { return false; }

	@Override
	public void tick(float delta, boolean moving) {
		//this.setRegion(moving ? Assets.mineActive : Assets.minePassive);
	}
}
