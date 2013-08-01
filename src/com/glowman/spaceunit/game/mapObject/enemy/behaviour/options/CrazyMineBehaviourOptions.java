package com.glowman.spaceunit.game.mapObject.enemy.behaviour.options;

import com.glowman.spaceunit.game.animation.IBlowMaker;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.core.BehaviourOptions;
import com.glowman.spaceunit.game.mapObject.impact.IImpactMaker;

/**
 *
 */
public class CrazyMineBehaviourOptions extends BehaviourOptions {
	public IBlowMaker blowMaker;
	public IImpactMaker impactMaker;

	public CrazyMineBehaviourOptions(IBlowMaker blowMaker, IImpactMaker impactMaker) {
		this.blowMaker = blowMaker;
		this.impactMaker = impactMaker;
	}
}
