package com.glowman.spaceunit.game.mapObject.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.glowman.spaceunit.game.mapObject.SpaceObject;
import com.glowman.spaceunit.game.mapObject.MovingSpaceObject;

/**
 *
 */
abstract public class Enemy extends MovingSpaceObject {
	protected SpaceObject _target;

	public Enemy(Sprite image, boolean randomScale, boolean teleportOnBorder)
	{
		super(image, randomScale, teleportOnBorder);
	}

	public void setTarget(SpaceObject target) {
		_target = target;
	}


}
