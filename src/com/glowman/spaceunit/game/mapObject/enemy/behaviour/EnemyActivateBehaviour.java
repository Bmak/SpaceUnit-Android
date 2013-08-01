package com.glowman.spaceunit.game.mapObject.enemy.behaviour;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.glowman.spaceunit.game.mapObject.enemy.Enemy;
import com.glowman.spaceunit.game.mapObject.enemy.ActiveEnemy;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.core.AEnemyBehaviour;

/**
 *
 */
public class EnemyActivateBehaviour extends AEnemyBehaviour {
	private Sprite _newLook;

	public EnemyActivateBehaviour(Enemy enemy) {
		super(EBehaviourENUM.ACTIVATE, enemy);

		if (!(_enemy instanceof ActiveEnemy)) {
			throw new Error("enemy need to be ActiveEnemy here!");
		}
	}

	@Override
	public void tick(float delta) {}

	@Override
	public void start() {
		super.start();
		ActiveEnemy enemy = (ActiveEnemy) _enemy;
		enemy.changeToActiveMode();
	}

	public void stop() {
		super.stop();
		ActiveEnemy enemy = (ActiveEnemy) _enemy;
		enemy.changeToPassiveMode();
	}
}
