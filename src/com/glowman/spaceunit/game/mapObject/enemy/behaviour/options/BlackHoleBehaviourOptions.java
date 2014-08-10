package com.glowman.spaceunit.game.mapObject.enemy.behaviour.options;

import com.glowman.spaceunit.game.mapObject.enemy.behaviour.core.BehaviourOptions;
import com.glowman.spaceunit.game.mapObject.impact.IImpactMaker;
/**
 * Created by Mishok on 29.07.14.
 */
public class BlackHoleBehaviourOptions extends BehaviourOptions {

    public IImpactMaker impactMaker;

    public BlackHoleBehaviourOptions( IImpactMaker impactMaker) {
        this.impactMaker = impactMaker;
    }
}
