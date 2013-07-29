package com.glowman.spaceunit.game.mapObject.enemy.behaviour.options;

import com.glowman.spaceunit.game.mapObject.SpaceObject;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.AEnemyBehaviour;

/**
 *
 */
public class AlarmBehaviourOptions extends BehaviourOptions {
	public float distance;
	public AEnemyBehaviour[] executeBehaviours;

	public AlarmBehaviourOptions(float distance, AEnemyBehaviour[] behaviours) {
		super();
		this.distance = distance;
		this.executeBehaviours = behaviours;
	}
}
