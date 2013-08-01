package com.glowman.spaceunit.game.mapObject.impact;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.glowman.spaceunit.game.mapObject.SpaceObject;

/**
 *
 */
public interface ISpaceImpact {
	void tick(float delta);

	/**
	 * @return Can be null
	 */
	Sprite getImage();

	void start();
	void stop();
	boolean isComplete();

	void execute(SpaceObject object);
}
