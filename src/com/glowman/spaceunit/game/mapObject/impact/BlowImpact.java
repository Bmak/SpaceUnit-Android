package com.glowman.spaceunit.game.mapObject.impact;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.game.animation.IBlowMaker;
import com.glowman.spaceunit.game.mapObject.SpaceObject;

/**
 *
 */
public class BlowImpact extends SpaceObject implements ISpaceImpact {
	private final float MAX_STRAIGHT = 1; //5 seconds active
	private final float MAX_SCALE = 3 * MAX_STRAIGHT;
	private final float KICK_STRAIGHT = 30;
	private float _currentStraight;

	private final IBlowMaker _blowMaker;

	public BlowImpact(float x, float y, IBlowMaker blowMaker) {
		super(new Sprite(Assets.blueCircle), false);
		super.setPosition(x - super.getWidth()/2, y - super.getHeight()/2);
		_blowMaker = blowMaker;
	}

	@Override
	public void start() {
		_currentStraight = MAX_STRAIGHT;
	}
	@Override
	public void stop() {
		_currentStraight = 0;
	}

	@Override
	public boolean isComplete() {
		return _currentStraight == 0;
	}

	@Override
	public void tick(float delta) {
		super.tick(delta);
		super._image.scale(delta * MAX_SCALE);
		super.setAlpha(_currentStraight / MAX_STRAIGHT);
		_currentStraight -= delta;
		if (_currentStraight < 0) { _currentStraight = 0; }
	}

	@Override
	public void execute(SpaceObject object) {
		Vector2 objCenter = object.getCenterPosition();
		Vector2 impactCenter = super.getCenterPosition();
		float maxDistance = (super.getWidth() * MAX_SCALE)/2;
		float distanceCoef = (maxDistance - objCenter.dst(impactCenter))/maxDistance;
		if (distanceCoef <= 0) { return; }

		if (distanceCoef > .7f) {
			object.explode(_blowMaker);
			object.setDead();
		}

		float impactValue = distanceCoef * KICK_STRAIGHT;

		float dx = objCenter.x - impactCenter.x;
		float dy = objCenter.y - impactCenter.y;
		float h = (float) Math.sqrt((double) (dx * dx + dy * dy) );
		float vx = 0;
		float vy = 0;
		if (h != 0){
			vx = (dx / h) * impactValue;
			vy = (dy / h) * impactValue;
		}

		Vector2 objPosition = object.getPosition();
		object.setPosition(objPosition.x + vx, objPosition.y + vy);
	}
}
