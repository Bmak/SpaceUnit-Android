package com.glowman.spaceunit.game.mapObject.enemy.behaviour;

import com.glowman.spaceunit.AppVibrator;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.SoundPlayer;
import com.glowman.spaceunit.game.balance.RotationSpeedCollector;
import com.glowman.spaceunit.game.mapObject.enemy.Enemy;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.core.AEnemyBehaviour;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.core.BehaviourOptions;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.options.BlackHoleBehaviourOptions;
import com.glowman.spaceunit.game.mapObject.impact.IImpactMaker;

/**
 * Created by Mishok on 29.07.14.
 */
public class EnemyBlackHoleBehaviour extends AEnemyBehaviour {

    private final IImpactMaker _impactMaker;
    private float _timeState = 0;

    public EnemyBlackHoleBehaviour(Enemy enemy, BehaviourOptions options) {
        super(EBehaviourENUM.BLACK_HOLE, enemy);
        if (!(options instanceof BlackHoleBehaviourOptions)) {
            throw new Error("invalid options");
        }

        BlackHoleBehaviourOptions bhOptions = (BlackHoleBehaviourOptions) options;
        _impactMaker = bhOptions.impactMaker;
    }



    @Override
    public void start() {
        _enemy.setRotationSpeed(RotationSpeedCollector.getRotationTurboSpeed());
        _timeState = 0;
    }

    @Override
    public void stop() {
        _enemy.resumeMoving();
    }

    @Override
    public void tick(float delta) {
        _timeState += delta;
        if (_timeState > 0) {
            _timeState = 0;
            _impactMaker.createGravity(_enemy.getCenterPosition().x, _enemy.getCenterPosition().y);
           // SoundPlayer.playSound(Assets.bigBamSound);
           // AppVibrator.getInstance().vibrate(300);
        }
    }
}
