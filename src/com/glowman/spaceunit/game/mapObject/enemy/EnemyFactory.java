package com.glowman.spaceunit.game.mapObject.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.game.IShooter;
import com.glowman.spaceunit.game.mapObject.Ship;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.*;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.options.AlarmBehaviourOptions;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.options.BehaviourOptions;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.options.ShootBehaviourOptions;
import com.glowman.spaceunit.game.strategy.GameStrategy;

/**
 *
 */
public class EnemyFactory {

	private static int _gameType = -1;
	private static Ship _heroShip;
	private static IShooter _shooter;

	public static void init(int gameType, Ship heroShip, IShooter shooter) {
		_gameType = gameType;
		_heroShip = heroShip;
		_shooter = shooter;
	}

	public static void init(int _gameType, Ship heroShip) {
		init(_gameType, heroShip, null);
	}

	public static Enemy createEnemy(String enemyType) {
		Enemy result;
		if (enemyType == EnemyTypeENUM.ASTEROID) {
			result = createAsteroid();
		}
		else if (enemyType == EnemyTypeENUM.MINE) {
			result = createMine();
		}
		else if (enemyType == EnemyTypeENUM.ALIEN) {
			result = createAlien();
		}
		else {
			throw new Error("unknown enemy");
		}
		return result;
	}

	private static Enemy createAsteroid() {
		Enemy result;
		Sprite image = new Sprite(Assets.soImages[Math.round((float)Math.random())]);
		result = new Enemy(EnemyTypeENUM.ASTEROID, image, true, true);
		if (_gameType == GameStrategy.RUN_GAME) {
			result.addBehaviour(new EnemyFollowBehaviour(result, _heroShip));
		}
		return result;
	}

	private static Enemy createMine() {
		Enemy result;
		Sprite passiveImage = new Sprite(Assets.minePassive);
		Sprite activeImage = new Sprite(Assets.mineActive);
		result = new ActiveEnemy(EnemyTypeENUM.MINE, passiveImage, activeImage, false, true);

		AEnemyBehaviour[] behavioursExecute = new AEnemyBehaviour[2];
		//behavioursExecute[0] = new EnemyFollowBehaviour(result, _heroShip);
		behavioursExecute[0] = new EnemyCrazyMineBehaviour(result);
		behavioursExecute[1] = new EnemyActivateBehaviour(result);

		BehaviourOptions options = new AlarmBehaviourOptions(result.getWidth() * 4, behavioursExecute);
		result.addBehaviour(new EnemyAlarmBehaviour(result, _heroShip, options));
		return result;
	}

	private static Enemy createAlien() {
		Enemy result;
		Sprite passiveImage = new Sprite(Assets.alienPassive);
		Sprite activeImage = new Sprite(Assets.alienActive);
		result = new ActiveEnemy(EnemyTypeENUM.ALIEN, passiveImage, activeImage, false, true);

		BehaviourOptions options = new ShootBehaviourOptions(6f, _heroShip, _shooter);
		result.addBehaviour(new EnemyAlienBehaviour(result, _heroShip, options));
		return result;
	}

}
