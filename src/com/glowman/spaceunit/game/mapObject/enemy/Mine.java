package com.glowman.spaceunit.game.mapObject.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.Assets;

/**
 *
 */
public class Mine extends Enemy {
	public Mine() {
		super(new Sprite(Assets.minePassive));
	}
}
