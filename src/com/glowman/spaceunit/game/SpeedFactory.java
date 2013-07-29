package com.glowman.spaceunit.game;

import com.glowman.spaceunit.game.mapObject.MovingSpaceObject;
import com.glowman.spaceunit.game.mapObject.SpaceObject;
import com.glowman.spaceunit.game.mapObject.enemy.Enemy;
import com.glowman.spaceunit.game.mapObject.enemy.EnemyTypeENUM;

/**
 *
 */
public class SpeedFactory {

	public static float getHeroSpeed(int gameType) {
		return 2.01f;
	}

	public static float getEnemySpeed(String enemyType, int gameType) {
		float result = 0f;
			//basic enemy (asteroids)
			if (enemyType == EnemyTypeENUM.ASTEROID) {
				result = (float)Math.random() * 2f;
			}
			//mine
			else if (enemyType == EnemyTypeENUM.MINE) {
				result = 1f;
			}
			//alien
			else if (enemyType == EnemyTypeENUM.ALIEN) {
				result = 1.6f;
			}
		return result;
	}

	public static float getMenuAsteroidSpeed() {
			return (float)Math.random() * 2f;
	}

	public static float getBulletSpeed(SpaceObject owner, int gameType) {
		return 5f;
	}


}
