package com.glowman.spaceunit.game.mapObject.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 */
public class StupidEnemy extends Enemy {

	public StupidEnemy(Sprite image) {
		super(image, true, true);
	}

	@Override
	public void tick(float delta) {
		if (super._target != null) {
			super.moveTo(_target.getPosition().x, _target.getPosition().y);
		}
		super.tick(delta);
	}

}
