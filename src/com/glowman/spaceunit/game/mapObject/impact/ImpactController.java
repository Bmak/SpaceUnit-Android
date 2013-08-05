package com.glowman.spaceunit.game.mapObject.impact;

import com.glowman.spaceunit.game.animation.IBlowMaker;
import com.glowman.spaceunit.game.mapObject.SpaceObject;

import java.util.ArrayList;

/**
 *
 */
public class ImpactController implements IImpactMaker {
	private ArrayList<ISpaceImpact> _impacts;

	private final IBlowMaker _blowMaker;

	public ImpactController(IBlowMaker blowMaker) {
		_impacts = new ArrayList<ISpaceImpact>();
		_blowMaker = blowMaker;
	}

	public void execute(SpaceObject object) {
		for (ISpaceImpact impact : _impacts) {
			impact.execute(object);
		}
	}

	public void tick(float delta) {
		ArrayList<ISpaceImpact> completedImpacts = new ArrayList<ISpaceImpact>();
		for (ISpaceImpact impact : _impacts) {
			if (impact.isComplete()) {
				completedImpacts.add(impact);
			} else {
				impact.tick(delta);
			}
		}

		for (ISpaceImpact impact : completedImpacts) {
			impact.stop();
			_impacts.remove(impact);
		}
		completedImpacts.clear();
	}

	public void createBlowImpact(float x, float y) {
		ISpaceImpact blowImpact = new BlowImpact(x, y, _blowMaker);
		blowImpact.start();
		_impacts.add(blowImpact);
	}

	public ArrayList<ISpaceImpact> getImpacts() { return _impacts; }
}
