package com.glowman.spaceunit.game.mapObject.enemy.behaviour;

import com.glowman.spaceunit.game.mapObject.SpaceObject;
import com.glowman.spaceunit.game.mapObject.enemy.Enemy;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.options.BehaviourOptions;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.options.ShootBehaviourOptions;

/**
 *
 */
public class EnemyShootBehaviour extends AEnemyBehaviourWithTarget{
	protected ShootBehaviourOptions _options;

	protected float _stateTime;

	public EnemyShootBehaviour(String name, Enemy enemy, SpaceObject target, BehaviourOptions options) {
		super(name, enemy, target);

		if (!(options instanceof ShootBehaviourOptions)) {
			throw new Error("options need to be ShootBehaviourOptions here");
		}

		_options = (ShootBehaviourOptions) options;
	}

	public EnemyShootBehaviour(Enemy enemy, SpaceObject target, BehaviourOptions options) {
		this(EnemyBehaviourNameENUM.SHOOT, enemy, target, options);
	}

	@Override
	public void start() {
		super.start();
		_stateTime = 0;
	}

	@Override
	public void stop() {
		super.stop();
		_stateTime = 0;
	}

	@Override
	public void tick(float delta) {
		_stateTime += delta;
		if (_stateTime >= _options.frequency) {
			_options.shooter.shoot(_enemy.getPosition(), _options.targetToShoot.getPosition());
			_stateTime %= _options.frequency;
		}
	}

}
