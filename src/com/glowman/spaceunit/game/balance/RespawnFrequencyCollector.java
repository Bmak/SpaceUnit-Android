package com.glowman.spaceunit.game.balance;

import com.glowman.spaceunit.game.mapObject.enemy.EnemyTypeENUM;
import com.glowman.spaceunit.game.strategy.GameStrategy;

/**
 *
 */
public class RespawnFrequencyCollector {

	//TODO game balance here
	public static float getFrequency(String enemyType, int gameType, float timeState) {
		float result;
		if (enemyType == EnemyTypeENUM.ASTEROID) {
			if (gameType == GameStrategy.RUN_GAME) {
				result =  0.2f * (timeState / 1000); //after 10 sec 0.2f
			} else {
				result = 0.7f * (timeState / 100);
			}
		}
		else if (enemyType == EnemyTypeENUM.MINE) {
			result = .03f * (timeState / 300);
		}
		else if (enemyType == EnemyTypeENUM.CRAZY_MINE) {
			result = .03f * (timeState / 400);
		}
		else if (enemyType == EnemyTypeENUM.ALIEN) {
			result = .01f * (timeState / 300);
		}
		else {
			throw new Error("unknown enemy : " + enemyType);
		}

		return result;
	}
}
