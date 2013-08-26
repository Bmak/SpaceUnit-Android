package com.glowman.spaceunit.game.strategy;

import com.glowman.spaceunit.game.mapObject.hero.Ship;

/**
 *
 */
public class GameStrategyFactory {

	/**
	 *
	 * @param ship
	 * @param gameType
	 * @return
	 * @throws Error "unknown game type"
	 */
	public static GameStrategy createStrategy(Ship ship, int gameType)
	{
		GameStrategy result;
		if (gameType == GameStrategy.RUN_GAME) {
			result = new GameRunStrategy(ship);
		} else if (gameType == GameStrategy.SHOOT_GAME) {
			result = new GameShootStrategy(ship);
		} else { throw new Error("unknown game type"); }
		return result;
	}
}
