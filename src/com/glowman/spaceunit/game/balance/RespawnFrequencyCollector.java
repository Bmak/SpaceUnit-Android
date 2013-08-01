package com.glowman.spaceunit.game.balance;

import com.glowman.spaceunit.game.mapObject.enemy.Enemy;
import com.glowman.spaceunit.game.mapObject.enemy.EnemyTypeENUM;
import com.glowman.spaceunit.game.strategy.GameStrategy;

/**
 *
 */
public class RespawnFrequencyCollector {

	//TODO game balance here
	public static float getFrequency(String enemyType, int gameType) {
		float result;
		if (enemyType == EnemyTypeENUM.ASTEROID) {
			result = gameType == GameStrategy.SHOOT_GAME ? .07f : 0.2f;
		}
		else if (enemyType == EnemyTypeENUM.MINE) {
			result = .03f;
		}
		else if (enemyType == EnemyTypeENUM.CRAZY_MINE) {
			result = .03f;
		}
		else if (enemyType == EnemyTypeENUM.ALIEN) {
			result = .01f;
		}
		else {
			throw new Error("unknown enemy : " + enemyType);
		}

		return result;
	}
}
