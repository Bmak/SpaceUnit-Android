package com.glowman.spaceunit.game.strategy;

import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.game.mapObject.Ship;

/**
 *
 */
public class GameStrategyFactory {

	/**
	 *
	 * @param ship
	 * @param screenSize
	 * @param gameType
	 * @return
	 * @throws Error "unknown game type"
	 */
	public static GameStrategy createStrategy(Ship ship, Vector2 screenSize, int gameType)
	{
		GameStrategy result;
		if (gameType == GameStrategy.RUN_GAME) {
			result = new GameRunStrategy(ship, screenSize);
		} else if (gameType == GameStrategy.SHOOT_GAME) {
			result = new GameShootStrategy(ship, screenSize);
		} else { throw new Error("unknown game type"); }
		return result;
	}
}
