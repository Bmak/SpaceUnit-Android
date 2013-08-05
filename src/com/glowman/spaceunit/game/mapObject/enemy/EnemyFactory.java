package com.glowman.spaceunit.game.mapObject.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.game.mapObject.MovingSpaceObject.BORDER_BEHAVIOUR;
import com.glowman.spaceunit.game.mapObject.Ship;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.EnemyActivateBehaviour;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.EnemyAlarmBehaviour;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.EnemyAlienBehaviour;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.EnemyCrazyMineBehaviour;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.EnemyFollowBehaviour;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.core.AEnemyBehaviour;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.options.AlarmBehaviourOptions;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.core.BehaviourOptions;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.options.CrazyMineBehaviourOptions;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.options.ShootBehaviourOptions;
import com.glowman.spaceunit.game.strategy.GameStrategy;

/**
 *
 */
public class EnemyFactory {

	private static int _gameType = -1;
	private static Ship _heroShip;
	private static BehaviourOptionsData _behaviourOptions;

	public static void init(int gameType, Ship heroShip, BehaviourOptionsData behaviourOptions) {
		_gameType = gameType;
		_heroShip = heroShip;
		_behaviourOptions = behaviourOptions;
	}

	public static Enemy createEnemy(String enemyType) {
		Enemy result;
		if (enemyType == EnemyTypeENUM.ASTEROID) {
			result = createAsteroid();
		}
		else if (enemyType == EnemyTypeENUM.MINE) {
			result = createMine();
		}
		else if (enemyType == EnemyTypeENUM.CRAZY_MINE) {
			result = createCrazyMine();
		}
		else if (enemyType == EnemyTypeENUM.ALIEN) {
			result = createAlien();
		}
		else {
			throw new Error("unknown enemy : " + enemyType);
		}
		return result;
	}

	/**
	 * He is same as Asteroid in Africa
	 * @return
	 */
	private static Enemy createAsteroid() {
		Enemy result;
		Sprite image = new Sprite(Assets.soImages[Math.round((float)Math.random())]);
		result = new Enemy(EnemyTypeENUM.ASTEROID, image, true, BORDER_BEHAVIOUR.TELEPORT);
		if (_gameType == GameStrategy.RUN_GAME) {
			result.addBehaviour(new EnemyFollowBehaviour(result, _heroShip));
		}
		return result;
	}

	/**
	 * Basic Mine. Alarm and follow hero
	 * @return
	 */
	private static Enemy createMine() {
		Enemy result;
		Sprite passiveImage = new Sprite(Assets.minePassive);
		Sprite activeImage = new Sprite(Assets.mineActive);
		result = new ActiveEnemy(EnemyTypeENUM.MINE, passiveImage, activeImage, false,
									BORDER_BEHAVIOUR.TELEPORT);

		AEnemyBehaviour[] behavioursExecute = new AEnemyBehaviour[2];
		behavioursExecute[0] = new EnemyFollowBehaviour(result, _heroShip);
		behavioursExecute[1] = new EnemyActivateBehaviour(result);

		BehaviourOptions options = new AlarmBehaviourOptions(result.getWidth() * 4, behavioursExecute);
		result.addBehaviour(new EnemyAlarmBehaviour(result, _heroShip, options));
		return result;
	}

	/**
	 * Crazy Mine. Alarm then turbo rotation and BOW with aftermath
	 * @return
	 */
	public static Enemy createCrazyMine() {
		Enemy result;
		Sprite passiveImage = new Sprite(Assets.minePassive);
		Sprite activeImage = new Sprite(Assets.mineActive);
		result = new ActiveEnemy(EnemyTypeENUM.CRAZY_MINE, passiveImage, activeImage, false,
									BORDER_BEHAVIOUR.TELEPORT);

		AEnemyBehaviour[] behavioursExecute = new AEnemyBehaviour[2];
		BehaviourOptions cmOptions = new CrazyMineBehaviourOptions(_behaviourOptions.blowMaker,
																	_behaviourOptions.impactMaker);
		behavioursExecute[0] = new EnemyCrazyMineBehaviour(result, cmOptions);
		behavioursExecute[1] = new EnemyActivateBehaviour(result);

		BehaviourOptions options = new AlarmBehaviourOptions(result.getWidth() * 4, behavioursExecute);
		result.addBehaviour(new EnemyAlarmBehaviour(result, _heroShip, options));
		return result;
	}

	/**
	 * Alien. Shooting to hero with some frequency
	 * @return
	 */
	private static Enemy createAlien() {
		Enemy result;
		Sprite passiveImage = new Sprite(Assets.alienPassive);
		Sprite activeImage = new Sprite(Assets.alienActive);
		result = new ActiveEnemy(EnemyTypeENUM.ALIEN, passiveImage, activeImage, false,
									BORDER_BEHAVIOUR.TELEPORT.TELEPORT);

		BehaviourOptions options = new ShootBehaviourOptions(6f, _heroShip, _behaviourOptions.shooter);
		result.addBehaviour(new EnemyAlienBehaviour(result, _heroShip, options));
		return result;
	}

}
