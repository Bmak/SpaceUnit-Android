package com.glowman.spaceunit.game.mapObject.gun;

import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.game.IShooter;
import com.glowman.spaceunit.game.mapObject.SpaceObject;

/**
 *
 */
public class AGun {
	private float _reloadTime;
	private float _reloadTimeLeft;
	private boolean _readyForShoot;

	public AGun(float reloadTime) {
		_reloadTime = reloadTime;
		_reloadTimeLeft = 0;
		_readyForShoot = false;
	}

	public boolean isReloading() { return _reloadTimeLeft > 0; }

	public void tick(float delta) {
		_reloadTimeLeft-= delta;

		if (_reloadTimeLeft <= 0)
		{
			_readyForShoot = true;
			_reloadTimeLeft = 0;
		}
	}

	public void shoot(IShooter shooter, SpaceObject owner, Vector2 target) {
		_readyForShoot = false;
		_reloadTimeLeft = _reloadTime;
	}

	public boolean isReadyForShoot() { return _readyForShoot; }

}
