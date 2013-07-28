package com.glowman.spaceunit.game.mapObject.enemy.behaviour;

import android.util.Log;
import com.glowman.spaceunit.game.mapObject.SpaceObject;
import com.glowman.spaceunit.game.mapObject.enemy.AEnemy;

/**
 *
 */
public class EnemyShootBehaviour extends AEnemyBehaviourWithTarget{
	public EnemyShootBehaviour(AEnemy enemy, SpaceObject target) {
		super(EnemyBehaviourNameENUM.SHOOT, enemy, target);
	}

	@Override
	public void tick(float delta) {
		//TODO shoot to target here
		Log.d("hz", "shot!");
	}

}
