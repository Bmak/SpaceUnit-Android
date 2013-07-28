package com.glowman.spaceunit.game.mapObject.enemy.behaviour;

import com.glowman.spaceunit.game.mapObject.enemy.AEnemy;

/**
 *
 */
abstract public class AEnemyBehaviour {
	private final String _behaviourName;
	protected final AEnemy _enemy;

	public AEnemyBehaviour(String name, AEnemy enemy) {
		_behaviourName = name;
		_enemy = enemy;
	}

	public String getName() { return _behaviourName; }

	abstract public void tick(float delta);
}
