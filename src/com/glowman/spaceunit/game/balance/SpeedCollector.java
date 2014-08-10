package com.glowman.spaceunit.game.balance;

import com.glowman.spaceunit.game.mapObject.SpaceObject;
import com.glowman.spaceunit.game.mapObject.enemy.EnemyTypeENUM;

/**
 *
 */
public class SpeedCollector {

	public static float getHeroSpeed(int gameType) {
		return 3.01f;
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
			//crazy mine
			else if (enemyType == EnemyTypeENUM.CRAZY_MINE) {
				result = 1f;
			}
			//alien
			else if (enemyType == EnemyTypeENUM.ALIEN) {
				result = 1.6f;
			}
            else if (enemyType == EnemyTypeENUM.BLACK_HOLE) {
                result = 1f;
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
