package com.glowman.spaceunit.game.mapObject.enemy.behaviour.core;

import com.glowman.spaceunit.game.mapObject.enemy.Enemy;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.EBehaviourENUM;

/**
 *
 */
abstract public class AEnemyBehaviour {
	private final EBehaviourENUM _behaviourName;
	protected final Enemy _enemy;
	protected final BehaviourOptions _options;

	protected boolean _actual;

	public AEnemyBehaviour(EBehaviourENUM name, Enemy enemy, BehaviourOptions options) {
		_behaviourName = name;
		_enemy = enemy;
		_options = options;
	}

	public AEnemyBehaviour(EBehaviourENUM name, Enemy enemy) {
		this(name, enemy, null);
	}

	public EBehaviourENUM getName() { return _behaviourName; }

	public void start() {
		_actual = true;
	}
	public void stop() {
		_actual = false;
	}

	public boolean isActual() { return _actual; }

	abstract public void tick(float delta);
}
