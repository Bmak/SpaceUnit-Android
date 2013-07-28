package com.glowman.spaceunit.game.mapObject.enemy.behaviour.options;

import com.glowman.spaceunit.game.mapObject.SpaceObject;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.AEnemyBehaviour;

/**
 *
 */
public class AlarmBehaviourOptions extends BehaviourOptions {
	public float distance;
	public AEnemyBehaviour[] executeBehaviours;

	public AlarmBehaviourOptions(SpaceObject target, float distance, AEnemyBehaviour[] behaviours) {
		super(target);
		this.distance = distance;
		this.executeBehaviours = behaviours;
	}
}
