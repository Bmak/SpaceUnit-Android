package com.glowman.spaceunit.game.mapObject.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.game.mapObject.SpaceObject;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.EnemyBehaviourNameENUM;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.EnemyFollowBehaviour;

/**
 *
 */
public class Mine extends ActiveEnemy {
	private final float BASIC_ALARM_RADIUS = 50 / Assets.pixelDensity;
	private float _alarm_radius;

	public Mine() {
		super(new Sprite(Assets.minePassive), new Sprite(Assets.mineActive), false, true);
	}

	@Override
	public void setTarget(SpaceObject target) {
		super.setTarget(target);
		_alarm_radius = BASIC_ALARM_RADIUS + super.getWidth()/2 + target.getWidth()/2;
	}

	@Override
	public void tick(float delta) {
		super.tick(delta);

		if (super.getCenterPosition().dst(_target.getCenterPosition()) < _alarm_radius) {
			if (!super.isActiveMode()) {
				super.changeToActiveMode();
				super.addBehaviour(new EnemyFollowBehaviour(this, _target));
			}
		}
		else if (super.isActiveMode()) {
			super.changeToPassiveMode();
			super.removeBehaviour(EnemyBehaviourNameENUM.FOLLOW);
		}
	}

}
