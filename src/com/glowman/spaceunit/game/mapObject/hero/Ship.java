package com.glowman.spaceunit.game.mapObject.hero;

import android.util.Log;
import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.game.IShooter;
import com.glowman.spaceunit.game.mapObject.MovingSpaceObject;
import com.glowman.spaceunit.game.mapObject.gun.AGun;


/**
 *
 */
public class Ship extends MovingSpaceObject {

    private final int ATTACH_POINT_DISTANCE = 5;

    private HeroSkin _skin;
    private AGun _gun;

    public float _targetX;
    public float _targetY;


    @Override
    public void moveTo(float targetX, float targetY) {
        super.moveTo( targetX, targetY);
        _targetX = targetX;
        _targetY = targetY;
    }


    public Ship(HeroSkin skin, BORDER_BEHAVIOUR bBehaviour)
    {
        super(skin, false, bBehaviour);
        _skin = skin;

    }

    public Ship(BORDER_BEHAVIOUR bBehaviour) {
        this(new BasicHeroSkin(), bBehaviour);
    }


    public void setSkin(HeroSkin skin)
    {
        _skin = skin;

        Vector2 centerPos = super.getCenterPosition();
        super.setPosition(centerPos.x - _skin.getWidth()/2,
                centerPos.y - _skin.getHeight()/2);

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

        if (this.isMoving() && this.isTargetPointReached())
        {
            this.stopMoving();
        }
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

    private boolean isTargetPointReached()
    {
        Vector2 shipPosition = this.getCenterPosition();
        float distToTarget = shipPosition.dst(_targetX, _targetY);

        return distToTarget < ATTACH_POINT_DISTANCE;
    }

}
