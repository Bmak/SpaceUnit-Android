package com.glowman.spaceunit.game.mapObject.impact;

import android.util.Log;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.game.animation.IBlowMaker;
import com.glowman.spaceunit.game.mapObject.SpaceObject;

/**
 * Created by Mishok on 30.07.14.
 */
public class GravityImpact extends SpaceObject implements ISpaceImpact {
    private final float MAX_STRAIGHT = 1; //5 seconds active
    private final float MAX_SCALE = 1 * MAX_STRAIGHT;
    private final float KICK_STRAIGHT = 30;
    private float _currentStraight;

    private final IBlowMaker _blowMaker;

    public GravityImpact(float x, float y, IBlowMaker blowMaker) {
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
        if (_currentStraight < 0) {
            _currentStraight = 0;

        }
    }

	@Override
	public boolean isUnderImpact(SpaceObject object)
	{
		Vector2 objCenter = object.getCenterPosition();
		Vector2 impactCenter = super.getCenterPosition();
		float maxDistance = (super.getWidth() * MAX_SCALE)/2;

		float distanceCoef = (maxDistance - objCenter.dst(impactCenter))/maxDistance;

		return distanceCoef > 0;
	}

    @Override
    public void execute(SpaceObject object) {
        Vector2 objCenter = object.getCenterPosition();
        Vector2 impactCenter = super.getCenterPosition();
        float maxDistance = (super.getWidth() * MAX_SCALE)/2;

        float distanceCoef = (maxDistance - objCenter.dst(impactCenter))/maxDistance;

        if (!this.isUnderImpact(object)) {
            return;
        }

        float impactValue = distanceCoef * KICK_STRAIGHT;

        float dx = objCenter.x - impactCenter.x;
        float dy = objCenter.y - impactCenter.y;
        float h = (float) Math.sqrt((double) (dx * dx + dy * dy) );
        float vx = 0;
        float vy = 0;
        if (h != 0){
            vx = (dx / h); //* impactValue;
            vy = (dy / h); //* impactValue;

        }

        Vector2 objPosition = object.getPosition();
        object.setPosition(objPosition.x - vx/2, objPosition.y - vy/2);

    }

}
