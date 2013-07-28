package com.glowman.spaceunit.game.mapObject.enemy.behaviour;

import com.glowman.spaceunit.game.mapObject.SpaceObject;
import com.glowman.spaceunit.game.mapObject.enemy.AEnemy;


/**
 *
 */
abstract public class AEnemyBehaviourWithTarget extends AEnemyBehaviour {

	protected SpaceObject _target;

	public AEnemyBehaviourWithTarget(String name, AEnemy enemy, SpaceObject target) {
		super(name, enemy);
		_target = target;
	}
}
