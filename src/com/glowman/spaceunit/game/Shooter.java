package com.glowman.spaceunit.game;

import android.widget.Space;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.game.mapObject.Bullet;
import com.glowman.spaceunit.game.mapObject.SpaceObject;

import java.util.ArrayList;

/**
 *
 */
public class Shooter implements IShooter {
	public static final float DEFAULT_BULLET_SPEED = 5;

	private float _bulletSpeed;

	private ArrayList<Bullet> _bullets;

	public Shooter(float bulletSpeed) {
		_bulletSpeed = bulletSpeed;
	}

	public Shooter() {
		this(DEFAULT_BULLET_SPEED);
	}

	@Override
	public void setBulletSpeed(float speed) {
		_bulletSpeed = speed;
	}
	@Override
	public float getBulletSpeed() { return _bulletSpeed; }

	@Override
	public void shoot(SpaceObject owner, Vector2 from, Vector2 to) {
		if (_bullets == null) {
			_bullets = new ArrayList<Bullet>();
		}

		Sprite bulletView = new Sprite(Assets.bullet);
		Bullet bullet = new Bullet(bulletView, owner);
		bullet.setGeneralSpeed(_bulletSpeed);
		bullet.setPosition(from.x, from.y);
		bullet.moveTo(to.x, to.y);
		bullet.rotateTo(to.x, to.y);
		_bullets.add(bullet);
	}

	@Override
	public void shoot(Vector2 from, Vector2 to) {
		this.shoot(null, from, to);
	}

	@Override
	public void shoot(SpaceObject owner, Vector2 to) {
		this.shoot(owner, owner.getCenterPosition(), to);
	}

	@Override
	public void shoot(Vector2 from, Vector2 to, float bulletSpeed) {
		float oldSpeed = _bulletSpeed;
		_bulletSpeed = bulletSpeed;
		this.shoot(from, to);
		_bulletSpeed = oldSpeed;
	}

	@Override
	public ArrayList<Bullet> getBullets() {
		return _bullets;
	}
}