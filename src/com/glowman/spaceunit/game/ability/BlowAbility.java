package com.glowman.spaceunit.game.ability;

import com.glowman.spaceunit.game.mapObject.hero.Ship;
import com.glowman.spaceunit.game.mapObject.impact.IImpactMaker;

/**
 *
 */
public class BlowAbility extends Ability {
	private IImpactMaker _imapctMaker;
	private Ship _heroShip;

	public BlowAbility(Ship heroShip, IImpactMaker impactMaker) {
		super(AbilityENUM.BLOW, 10);
		_heroShip = heroShip;
		_imapctMaker = impactMaker;
	}

	@Override
	public void activate() {
		super.activate();
		_imapctMaker.createAbilityBlow(_heroShip.getCenterPosition().x,
									   _heroShip.getCenterPosition().y);
	}
}
