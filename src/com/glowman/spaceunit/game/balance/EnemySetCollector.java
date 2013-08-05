package com.glowman.spaceunit.game.balance;

import com.glowman.spaceunit.game.mapObject.enemy.EnemyTypeENUM;
import com.glowman.spaceunit.game.strategy.GameStrategy;

/**
 *
 */
public class EnemySetCollector {

	public static String[] getEnemySet(int strategy) {
		String[] result;
		if (strategy == GameStrategy.RUN_GAME) {
			result = new String[1];
			result[0] = EnemyTypeENUM.ASTEROID;
		}
		else if (strategy == GameStrategy.SHOOT_GAME) {
			result = new String[4];
			result[0] = EnemyTypeENUM.ASTEROID;
			result[1] = EnemyTypeENUM.ALIEN;
			result[2] = EnemyTypeENUM.MINE;
			result[3] = EnemyTypeENUM.CRAZY_MINE;
		}
		else {
			throw new Error("unknown game strategy");
		}
		return result;
	}
}
