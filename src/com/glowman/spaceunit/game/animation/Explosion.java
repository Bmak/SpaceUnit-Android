package com.glowman.spaceunit.game.animation;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.game.mapObject.SpaceObject;

/**
 *
 */
public class Explosion extends SpaceObject {
	
	public Explosion() {
		super(new Sprite(Assets.blueCircle), false);
	}

	public void tick(float delta) {
		super.setScale(super.getScale() + .01f);
		if (super.getScale() >= 2f) {

		}
	}
	
	
}
