package com.glowman.spaceunit.game.animation;

import android.util.Log;
import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.core.AnimatedSprite;
import com.glowman.spaceunit.game.mapObject.SpaceObject;

/**
 *
 */
public class BlowAnimation extends AnimatedSprite {
	private final float SCALE = 1.5f;

	public BlowAnimation(float x, float y, float radius)
	{
		super(Assets.blowArray);
		super.setSize(radius * SCALE, radius * SCALE);
		super.setOrigin(super.getWidth() / 2, super.getHeight() / 2);
		float animationX = x - super.getOriginX();
		float animationY = y - super.getOriginY();
		super.setPosition(animationX, animationY);
	}

	public BlowAnimation(Vector2 position, float radius) {
		this(position.x, position.y, radius);
	}
}
