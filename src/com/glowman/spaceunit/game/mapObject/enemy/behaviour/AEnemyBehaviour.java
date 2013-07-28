package com.glowman.spaceunit.game.mapObject.enemy.behaviour;

import com.glowman.spaceunit.game.mapObject.enemy.Enemy;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.options.BehaviourOptions;

/**
 *
 */
abstract public class AEnemyBehaviour {
	private final String _behaviourName;
	protected final Enemy _enemy;
	protected final BehaviourOptions _options;

	protected boolean _actual;

	public AEnemyBehaviour(String name, Enemy enemy, BehaviourOptions options) {
		_behaviourName = name;
		_enemy = enemy;
		_options = options;
	}

	public AEnemyBehaviour(String name, Enemy enemy) {
		this(name, enemy, null);
	}

	public String getName() { return _behaviourName; }

	public void start() {
		_actual = true;
	}
	public void stop() {
		_actual = false;
	}

	public boolean isActual() { return _actual; }

	abstract public void tick(float delta);
}
