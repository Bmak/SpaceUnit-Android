package com.glowman.spaceunit.game.mapObject.enemy.behaviour;

import com.glowman.spaceunit.game.mapObject.SpaceObject;
import com.glowman.spaceunit.game.mapObject.enemy.Enemy;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.core.AEnemyBehaviour;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.options.AlarmBehaviourOptions;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.core.BehaviourOptions;

/**
 *
 */
public class EnemyAlarmBehaviour extends AEnemyBehaviourWithTarget {
	private AlarmBehaviourOptions _options;

	public EnemyAlarmBehaviour(Enemy enemy, SpaceObject target, BehaviourOptions options) {
		super(EBehaviourENUM.ALARM, enemy, target);

		if (!(options instanceof AlarmBehaviourOptions)) {
			throw new Error("need only AlarmBehaviourOptions here");
		}

		_options = (AlarmBehaviourOptions) options;
	}

	@Override
	public void tick(float delta) {
		if (_enemy.getCenterPosition().dst(_target.getCenterPosition()) < _options.distance) {

			for (AEnemyBehaviour behaviour : _options.executeBehaviours) {
				if (!_enemy.hasBehaviour(behaviour.getName())) {
					_enemy.addBehaviour(behaviour);
				}
			}
		}
		else {
			for (AEnemyBehaviour behaviour : _options.executeBehaviours) {
				if (_enemy.hasBehaviour(behaviour.getName())) {
					_enemy.removeBehaviour(behaviour.getName());
				}
			}
		}

	}
}
