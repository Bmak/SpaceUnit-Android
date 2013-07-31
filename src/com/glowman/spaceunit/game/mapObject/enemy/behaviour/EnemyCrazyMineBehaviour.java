package com.glowman.spaceunit.game.mapObject.enemy.behaviour;

import com.glowman.spaceunit.game.balance.RotationSpeedCollector;
import com.glowman.spaceunit.game.mapObject.enemy.Enemy;

/**
 *
 */
public class EnemyCrazyMineBehaviour extends AEnemyBehaviour {
	private float _enemyRotation;

	public EnemyCrazyMineBehaviour(Enemy enemy) {
		super(EnemyBehaviourNameENUM.CRAZY_MINE, enemy);
		_enemyRotation = enemy.getRotationSpeed();
	}

	@Override
	public void start() {
		_enemy.setRotationSpeed(RotationSpeedCollector.getRotationTurboSpeed());
		_enemy.stopMoving();
	}

	@Override
	public void stop() {
		_enemy.resumeMoving();
		_enemy.setRotationSpeed(_enemyRotation);
	}

	@Override
	public void tick(float delta) {

	}
}
