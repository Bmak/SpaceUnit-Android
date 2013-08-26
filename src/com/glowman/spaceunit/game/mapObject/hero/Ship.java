package com.glowman.spaceunit.game.mapObject.hero;

import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.game.IShooter;
import com.glowman.spaceunit.game.mapObject.MovingSpaceObject;
import com.glowman.spaceunit.game.mapObject.gun.AGun;

/**
 *
 */
public class Ship extends MovingSpaceObject {

	private HeroSkin _skin;
	private AGun _gun;

	public Ship(HeroSkin skin)
	{
		super(skin, false, BORDER_BEHAVIOUR.STOP);
		_skin = skin;
	}

	public Ship() {
		this(new BasicHeroSkin());
	}

	public void setSkin(HeroSkin skin)
	{
		_skin = skin;
		super.setImage(skin);
	}
	public HeroSkin getSkin() { return _skin; }

	public void setGun(AGun gun) {
		_gun = gun;
	}
	public AGun getGun() { return _gun; }

	@Override
	public void tick(float deltaTime)
	{
		super.tick(deltaTime);
		_skin.tick(deltaTime, super.isMoving());
		if (_gun != null) { _gun.tick(deltaTime); }
	}

	public void shoot(IShooter shooter, float targetX, float targetY) {
		if (_gun == null) {
			throw new Error("cant shoot, gun not equiped");
		}
		if (_gun.isReadyForShoot()) {
			_gun.shoot(shooter, this, new Vector2(targetX, targetY));
		}
	}

	@Override
	public void moveTo(float x, float y)
	{
	}

	@Override
	public void setDead() {
		super.setDead();
		_gun = null;
	}

	public void moveOn(float dx, float dy) {
		float h = (float) Math.sqrt((double) (dx * dx + dy * dy) );
		float vx = dx / h * _generalSpeed;
		float vy = dy / h * _generalSpeed;

		super.setVelocity(vx, vy);
		if (_skin.rotatable()) {
			super.rotateTo(_position.x + vx, _position.y + vy);
		}
	}

}
