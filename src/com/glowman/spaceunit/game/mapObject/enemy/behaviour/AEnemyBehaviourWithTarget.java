package com.glowman.spaceunit.game.mapObject.enemy.behaviour;

import com.glowman.spaceunit.game.mapObject.SpaceObject;
import com.glowman.spaceunit.game.mapObject.enemy.Enemy;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.options.BehaviourOptions;


/**
 *
 */
abstract public class AEnemyBehaviourWithTarget extends AEnemyBehaviour {

	protected SpaceObject _target;

	public AEnemyBehaviourWithTarget(String name, Enemy enemy, SpaceObject target,
									 BehaviourOptions options) {
		super(name, enemy, options);
		_target = target;
	}

	public AEnemyBehaviourWithTarget(String name, Enemy enemy, SpaceObject target) {
		this(name, enemy, target, null);
	}
}
