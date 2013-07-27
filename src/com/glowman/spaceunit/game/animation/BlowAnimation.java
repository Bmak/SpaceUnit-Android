package com.glowman.spaceunit.game.animation;

import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.core.AnimatedSprite;
import com.glowman.spaceunit.game.mapObject.SpaceObject;

/**
 *
 */
public class BlowAnimation extends AnimatedSprite {
	private final float SCALE = 1.5f;

	public BlowAnimation(SpaceObject enemy)
	{
		super(Assets.blowArray);
		Vector2 enemyCenterPoint = enemy.getCenterPosition();
		super.setSize(enemy.getWidth() * SCALE, enemy.getHeight() * SCALE);
		super.setOrigin(super.getWidth() / 2, super.getHeight() / 2);
		float animationX = enemyCenterPoint.x - super.getOriginX();
		float animationY = enemyCenterPoint.y - super.getOriginY();
		super.setPosition(animationX, animationY);
	}
}
