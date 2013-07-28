package com.glowman.spaceunit.game.mapObject.enemy.behaviour;

import com.glowman.spaceunit.game.mapObject.SpaceObject;
import com.glowman.spaceunit.game.mapObject.enemy.AEnemy;

/**
 *
 */
public class EnemyFollowBehaviour extends AEnemyBehaviourWithTarget {

	public EnemyFollowBehaviour(AEnemy enemy, SpaceObject target) {
		super(EnemyBehaviourNameENUM.FOLLOW, enemy, target);
	}

	@Override
	public void tick(float delta) {
		_enemy.moveTo(_target.getPosition().x, _target.getPosition().y);
	}
}
