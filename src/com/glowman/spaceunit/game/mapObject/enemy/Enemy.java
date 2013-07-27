package com.glowman.spaceunit.game.mapObject.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.game.mapObject.MovingSpaceObject;

/**
 *
 */
public class Enemy extends MovingSpaceObject {


	public Enemy(Sprite image, boolean teleportOnBorder)
	{
		super(image, true, teleportOnBorder);
	}

	public Enemy(Sprite image)
	{
		this(image, false);
	}

	@Override
	public void tick(float deltaTime) {
		super.tick(deltaTime);
	}
	
}
