package com.glowman.spaceunit.game.mapObject.enemy.behaviour;

import com.glowman.spaceunit.game.mapObject.SpaceObject;
import com.glowman.spaceunit.game.mapObject.enemy.ActiveEnemy;
import com.glowman.spaceunit.game.mapObject.enemy.Enemy;
import com.glowman.spaceunit.game.mapObject.enemy.EnemyTypeENUM;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.core.BehaviourOptions;

/**
 *
 */
public class EnemyAlienBehaviour extends EnemyShootBehaviour {

	public EnemyAlienBehaviour(Enemy enemy, SpaceObject target, BehaviourOptions options) {
		super(EBehaviourENUM.ALIEN, enemy, target, options);

		if (enemy.getEnemyType() != EnemyTypeENUM.ALIEN) {
			throw new Error("enemy need to be ALIEN");
		}
	}

	@Override
	public void tick(float delta) {
		super.tick(delta);
		//TODO Game Balance here
		ActiveEnemy activeEnemy = (ActiveEnemy) _enemy;
		if (super._stateTime / super._options.frequency  > 0.8f) {
			if (!activeEnemy.isActiveMode()) activeEnemy.changeToActiveMode();
		} else {
			if (activeEnemy.isActiveMode()) activeEnemy.changeToPassiveMode();
		}
	}
}
