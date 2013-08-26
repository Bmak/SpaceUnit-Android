package com.glowman.spaceunit.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.SoundPlayer;
import com.glowman.spaceunit.game.mapObject.Bullet;
import com.glowman.spaceunit.game.mapObject.SpaceObject;

import java.util.ArrayList;

/**
 *
 */
public class Shooter implements IShooter {
	public static final float DEFAULT_BULLET_SPEED = 5;

	public static final int HERO_BULLET = 0;
	public static final int ENEMY_BULLET = 1;

	private float _bulletSpeed;

	private ArrayList<Bullet> _bullets;

	public Shooter(float bulletSpeed) {
		_bulletSpeed = bulletSpeed;
		_bullets = new ArrayList<Bullet>();
	}

	public Shooter() {
		this(DEFAULT_BULLET_SPEED);
	}

	@Override
	public void tick(float delta) {

		ArrayList<Bullet> deadBullets = null;

		for (Bullet bullet : _bullets)
		{
			if (bullet.isDead()) {
				if (deadBullets == null) {
					deadBullets = new ArrayList<Bullet>();
				}
				deadBullets.add(bullet);
			}
			else {
				bullet.tick(delta);
			}
		}

		if (deadBullets != null) {
			for (Bullet bullet : deadBullets) { _bullets.remove(bullet); }
			deadBullets.clear();
		}
	}

	@Override
	public void shoot(SpaceObject owner, Vector2 from, Vector2 to, int bulletType) {
		if (_bullets == null) {
			_bullets = new ArrayList<Bullet>();
		}

		Sprite bulletView;
		if (bulletType == HERO_BULLET) {
			bulletView = new Sprite(Assets.bullet);
		} else {
			bulletView = new Sprite(Assets.enemyBullet);
		}
		Bullet bullet = new Bullet(bulletView, owner);
		bullet.setGeneralSpeed(_bulletSpeed);
		bullet.setPosition(from.x - bullet.getWidth()/2, from.y - bullet.getHeight()/2);
		bullet.moveTo(to.x, to.y);
		bullet.rotateTo(to.x, to.y);
		_bullets.add(bullet);
		SoundPlayer.playSound(Assets.shotSound, 0.2f);
	}

	@Override
	public void shoot(Vector2 from, Vector2 to, int bulletType) {
		this.shoot(null, from, to, bulletType);
	}

	@Override
	public void shoot(SpaceObject owner, Vector2 to, int bulletType) {
		this.shoot(owner, owner.getCenterPosition(), to, bulletType);
	}

	@Override
	public ArrayList<Bullet> getBullets() {
		return _bullets;
	}
}
