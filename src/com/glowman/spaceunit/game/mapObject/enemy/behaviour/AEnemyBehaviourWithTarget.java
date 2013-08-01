package com.glowman.spaceunit.game.mapObject.enemy.behaviour;

import com.glowman.spaceunit.game.mapObject.SpaceObject;
import com.glowman.spaceunit.game.mapObject.enemy.Enemy;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.core.AEnemyBehaviour;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.core.BehaviourOptions;


/**
 *
 */
abstract public class AEnemyBehaviourWithTarget extends AEnemyBehaviour {

	protected SpaceObject _target;

	public AEnemyBehaviourWithTarget(EBehaviourENUM name, Enemy enemy, SpaceObject target,
									 BehaviourOptions options) {
		super(name, enemy, options);
		_target = target;
	}

	public AEnemyBehaviourWithTarget(EBehaviourENUM name, Enemy enemy, SpaceObject target) {
		this(name, enemy, target, null);
	}
}
