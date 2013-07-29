package com.glowman.spaceunit.game.mapObject.enemy.behaviour;

import android.util.Log;
import com.glowman.spaceunit.game.mapObject.SpaceObject;
import com.glowman.spaceunit.game.mapObject.enemy.Enemy;

/**
 *
 */
public class EnemyFollowBehaviour extends AEnemyBehaviourWithTarget {

	public EnemyFollowBehaviour(Enemy enemy, SpaceObject target) {
		super(EnemyBehaviourNameENUM.FOLLOW, enemy, target);
	}

	@Override
	public void tick(float delta) {
		if (_enemy == null) {
			Log.d("hz", "enemy is null....");
		}

		if (_target == null) {
			Log.d("hz", "target is null ..!");
		}

		if (_target.getPosition() == null) {
			Log.d("hz", "target position is null....... cant understand");
		}

		_enemy.moveTo(_target.getPosition().x, _target.getPosition().y);
	}
}
