package com.glowman.spaceunit.game.mapObject.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.game.mapObject.Ship;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.AEnemyBehaviour;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.EnemyActivateBehaviour;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.EnemyAlarmBehaviour;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.EnemyFollowBehaviour;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.options.AlarmBehaviourOptions;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.options.BehaviourOptions;
import com.glowman.spaceunit.game.strategy.GameStrategy;

/**
 *
 */
public class EnemyFactory {

	private static int _gameType = -1;

	public static void setGameType(int gameType) { _gameType = gameType; }

	//TODO game balance here
	public static Enemy createEnemy(Ship heroShip) {

		if (_gameType == -1) { throw new Error("game type not inited!"); }
		Enemy result;
		double random = Math.random();
		if (random < .33 || _gameType == GameStrategy.RUN_GAME) {
			//asteroid enemy

			Sprite image = new Sprite(Assets.soImages[Math.round((float)Math.random())]);
			result = new Enemy(EnemyTypeENUM.ASTEROID, image, true, true);
			if (_gameType == GameStrategy.RUN_GAME) {
				result.addBehaviour(new EnemyFollowBehaviour(result, heroShip));
			}
		} else if (random < .63) {
			//mine

			Sprite passiveImage = new Sprite(Assets.minePassive);
			Sprite activeImage = new Sprite(Assets.mineActive);
			result = new ActiveEnemy(EnemyTypeENUM.MINE, passiveImage, activeImage, false, true);

			AEnemyBehaviour[] behavioursExecute = new AEnemyBehaviour[2];
			behavioursExecute[0] = new EnemyFollowBehaviour(result, heroShip);
			behavioursExecute[1] = new EnemyActivateBehaviour(result);

			BehaviourOptions options = new AlarmBehaviourOptions(heroShip, 100, behavioursExecute);
			result.addBehaviour(new EnemyAlarmBehaviour(result, heroShip, options));
		} else {
			//alien
			//TODO need to fill
			Sprite passiveImage = new Sprite(Assets.alienPassive);
			Sprite activeImage = new Sprite(Assets.alienActive);
			result = new ActiveEnemy(EnemyTypeENUM.ALIEN, passiveImage, activeImage, false, true);
		}

		return result;
	}
}
