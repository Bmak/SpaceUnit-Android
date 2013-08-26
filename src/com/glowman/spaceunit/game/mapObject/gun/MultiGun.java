package com.glowman.spaceunit.game.mapObject.gun;

import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.game.IShooter;
import com.glowman.spaceunit.game.Shooter;
import com.glowman.spaceunit.game.mapObject.SpaceObject;

/**
 *
 */
public class MultiGun extends AGun {
	private final int GUNS_NUM = 5;
	public MultiGun() {
		super(.2f);
	}

	@Override
	public void shoot(IShooter shooter, SpaceObject owner, Vector2 target) {
		super.shoot(shooter, owner, target);
		double step = Math.PI * 2 / GUNS_NUM;
		float targetX, targetY;
		float fromX, fromY;
		float cos, sin;
		double ownerAngle = owner.getImage().getRotation() / 180 * Math.PI;
		for (double angle = 0; angle < Math.PI * 2; angle += step) {
			cos = (float)Math.cos(angle + ownerAngle);
			sin = (float)Math.sin(angle + ownerAngle);
			targetX = owner.getCenterPosition().x + cos * 100;
			targetY = owner.getCenterPosition().y + sin * 100;
			fromX = owner.getCenterPosition().x + cos * owner.getWidth()/2;
			fromY = owner.getCenterPosition().y + sin * owner.getWidth()/2;
			shooter.shoot(owner, new Vector2(fromX, fromY), new Vector2(targetX, targetY), Shooter.HERO_BULLET);
		}
	}
}
