package com.glowman.spaceunit.game.mapObject.gun;

import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.game.IShooter;
import com.glowman.spaceunit.game.Shooter;
import com.glowman.spaceunit.game.mapObject.Bullet;
import com.glowman.spaceunit.game.mapObject.SpaceObject;

/**
 *
 */
public class SimpleGun extends AGun {

	public SimpleGun(float reloadTime) {
		super(reloadTime);
	}

	public SimpleGun() {
		this(.5f);
	}

	@Override
	public void shoot(IShooter shooter, SpaceObject owner, Vector2 target) {
		super.shoot(shooter, owner, target);
		shooter.shoot(owner, target, Shooter.HERO_BULLET);
	}

}
