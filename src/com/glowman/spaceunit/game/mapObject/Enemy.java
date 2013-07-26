package com.glowman.spaceunit.game.mapObject;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 *
 */
public class Enemy extends MovingSpaceObject {


	public Enemy(Sprite image, Vector2 screenSize, boolean teleportOnBorder)
	{
		super(image, screenSize, true, teleportOnBorder);
	}

	public Enemy(Sprite image, Vector2 screenSize)
	{
		this(image, screenSize, false);
	}

	@Override
	public void tick(float deltaTime) {
		super.tick(deltaTime);
	}
	
}
