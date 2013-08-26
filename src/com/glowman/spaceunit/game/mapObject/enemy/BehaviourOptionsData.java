package com.glowman.spaceunit.game.mapObject.enemy;

import com.glowman.spaceunit.game.IShooter;
import com.glowman.spaceunit.game.animation.IBlowMaker;
import com.glowman.spaceunit.game.mapObject.hero.Ship;
import com.glowman.spaceunit.game.mapObject.impact.IImpactMaker;

/**
 *
 */
public class BehaviourOptionsData {
	public IShooter shooter;
	public IBlowMaker blowMaker;
	public Ship heroShip;
	public IImpactMaker impactMaker;


	public BehaviourOptionsData(IShooter shooter, IBlowMaker blowMaker,
								Ship heroShip, IImpactMaker impactMaker) {
		this.shooter = shooter;
		this.blowMaker = blowMaker;
		this.heroShip = heroShip;
		this.impactMaker = impactMaker;
	}
}
