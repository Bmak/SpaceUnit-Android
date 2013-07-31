package com.glowman.spaceunit.game.animation;

import java.util.ArrayList;

/**
 *
 */
public class BlowController implements IBlowMaker {
	private ArrayList<BlowAnimation> _blows;

	public BlowController() {
		_blows = null;
	}

	public void tick(float delta) {
		if (_blows != null) {
			ArrayList<BlowAnimation> animationsForRemove = new ArrayList<BlowAnimation>();
			for (BlowAnimation blow : _blows)
			{
				if (blow.isAnimationFinished())
				{
					animationsForRemove.add(blow);
				} else
				{
					blow.tick(delta);
				}
			}
			for (BlowAnimation blow : animationsForRemove)
			{
				_blows.remove(blow);
			}
			animationsForRemove.clear();
		}
	}

	public void makeBlow(float x, float y, float width) {
		BlowAnimation blow = new BlowAnimation(x, y, width);
		if (_blows == null) {
			_blows = new ArrayList<BlowAnimation>();
		}
		_blows.add(blow);
	}

	public ArrayList<BlowAnimation> getBlows() { return _blows; }
}
