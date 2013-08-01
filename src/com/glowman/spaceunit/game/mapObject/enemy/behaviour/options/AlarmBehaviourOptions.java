package com.glowman.spaceunit.game.mapObject.enemy.behaviour.options;

import com.glowman.spaceunit.game.mapObject.enemy.behaviour.core.AEnemyBehaviour;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.core.BehaviourOptions;

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
