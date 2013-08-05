package com.glowman.spaceunit.game.mapObject.enemy.behaviour;

import android.os.Vibrator;
import com.glowman.spaceunit.AppVibrator;
import com.glowman.spaceunit.game.animation.IBlowMaker;
import com.glowman.spaceunit.game.balance.RotationSpeedCollector;
import com.glowman.spaceunit.game.mapObject.enemy.Enemy;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.core.AEnemyBehaviour;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.core.BehaviourOptions;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.options.CrazyMineBehaviourOptions;
import com.glowman.spaceunit.game.mapObject.impact.IImpactMaker;

/**
 *
 */
public class EnemyCrazyMineBehaviour extends AEnemyBehaviour {
	private final float BOOM_TIME = 3;

	private final float _enemyRotation;
	private float _timeState;
	private final IBlowMaker _blowMaker;
	private final IImpactMaker _impactMaker;

	public EnemyCrazyMineBehaviour(Enemy enemy, BehaviourOptions options) {
		super(EBehaviourENUM.CRAZY_MINE, enemy);
		_enemyRotation = enemy.getRotationSpeed();

		if (!(options instanceof CrazyMineBehaviourOptions)) {
			throw new Error("invalid options");
		}
		CrazyMineBehaviourOptions cmOptions = (CrazyMineBehaviourOptions) options;
		_blowMaker = cmOptions.blowMaker;
		_impactMaker = cmOptions.impactMaker;
	}

	@Override
	public void start() {
		_enemy.setRotationSpeed(RotationSpeedCollector.getRotationTurboSpeed());
		_enemy.stopMoving();
		_timeState = 0;
	}

	@Override
	public void stop() {
		_enemy.resumeMoving();
		_enemy.setRotationSpeed(_enemyRotation);
	}

	@Override
	public void tick(float delta) {
		_timeState += delta;
		if (_timeState > BOOM_TIME) {
			_enemy.explode(_blowMaker);
			_impactMaker.createBlowImpact(_enemy.getCenterPosition().x, _enemy.getCenterPosition().y);
			_enemy.setDead();
			AppVibrator.getInstance().vibrate(300);
		}
	}
}
