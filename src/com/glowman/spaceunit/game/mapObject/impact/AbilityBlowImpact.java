package com.glowman.spaceunit.game.mapObject.impact;

import com.glowman.spaceunit.game.animation.IBlowMaker;
import com.glowman.spaceunit.game.mapObject.Ship;
import com.glowman.spaceunit.game.mapObject.SpaceObject;

/**
 *
 */
public class AbilityBlowImpact extends BlowImpact {
	public AbilityBlowImpact(float x, float y, IBlowMaker blowMaker) {
		super(x, y, blowMaker);
	}

	@Override
	public void execute(SpaceObject object) {
		if (!(object instanceof Ship)) {
			super.execute(object);
		}
	}

}
