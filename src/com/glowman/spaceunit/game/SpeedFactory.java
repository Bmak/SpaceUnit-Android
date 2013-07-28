package com.glowman.spaceunit.game;

import com.glowman.spaceunit.game.mapObject.MovingSpaceObject;
import com.glowman.spaceunit.game.mapObject.Ship;
import com.glowman.spaceunit.game.mapObject.SpaceObject;
import com.glowman.spaceunit.game.mapObject.enemy.Alien;
import com.glowman.spaceunit.game.mapObject.enemy.Mine;
import com.glowman.spaceunit.game.mapObject.enemy.StupidEnemy;

/**
 *
 */
public class SpeedFactory {

	public static float getSpeed(SpaceObject object, int gameType) {
		float result = 0f;
		if (object instanceof MovingSpaceObject) {
			// ship speed
			if (object instanceof Ship) {
				result = 2.01f;
			}
			// basic space object (asteroids)
			else if (object instanceof StupidEnemy) {
				result = (float)Math.random() * 2f;
			}
			//mine
			else if (object instanceof Mine) {
				return 1f;
			}
			//alien
			else if (object instanceof Alien) {
				return 1.6f;
			}
		}

		return result;
	}
}
