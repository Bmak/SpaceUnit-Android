package com.glowman.spaceunit.game.mapObject.enemy.behaviour.options;

import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.game.IShooter;
import com.glowman.spaceunit.game.mapObject.SpaceObject;

/**
 *
 */
public class ShootBehaviourOptions extends BehaviourOptions {
	public float frequency;
	public SpaceObject targetToShoot;
	public IShooter shooter;

	public ShootBehaviourOptions(float frequency, SpaceObject targetToShoot, IShooter shooter) {
		super();
		this.frequency = frequency;
		this.targetToShoot = targetToShoot;
		this.shooter = shooter;
	}
}
