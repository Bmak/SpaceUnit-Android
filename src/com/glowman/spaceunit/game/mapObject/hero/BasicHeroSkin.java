package com.glowman.spaceunit.game.mapObject.hero;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.core.AnimatedSprite;

/**
 *
 */
public class BasicHeroSkin extends HeroSkin {
	private AnimatedSprite _shipAnim;
	private TextureRegion _calmState;

	public BasicHeroSkin() {
		super(Assets.ship);
		_calmState = Assets.ship;
		_shipAnim = new AnimatedSprite(Assets.shipArray, 0.1f, Animation.LOOP_PINGPONG);
	}

	@Override
	public boolean rotatable() { return true; }

	@Override
	public void tick(float delta, boolean moving) {
		if (moving) {
			_shipAnim.tick(delta);
			this.setRegion(_shipAnim.getCurrentRegion());
		}
		else {
			this.setRegion(_calmState);
		}

	}
}
