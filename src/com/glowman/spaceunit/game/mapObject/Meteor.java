package com.glowman.spaceunit.game.mapObject;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Meteor extends Enemy {
	
	
	public Meteor(Sprite image, Vector2 screenSize, boolean teleportOnBorder)
	{
		super(image, screenSize, teleportOnBorder);
		_speedRotate = 0;
		_speedX = 0;
		_speedY = 0;
		
		super.setRandomBehaviour();
	}

	public Meteor(Sprite image, Vector2 screenSize)
	{
		this(image, screenSize, false);
	}
	
	@Override
	public void tick(float deltaTime) {
		simpleMove();
		
		super.tick(deltaTime);
	}
	
	@Override
	public void setRandomBorderPosition() {
		super.setRandomBorderPosition();
		super.setRandomBehaviour();
	}
}
