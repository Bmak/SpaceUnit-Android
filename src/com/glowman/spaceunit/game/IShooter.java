package com.glowman.spaceunit.game;

import android.widget.Space;
import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.game.mapObject.Bullet;
import com.glowman.spaceunit.game.mapObject.SpaceObject;

import java.util.ArrayList;

/**
 *
 */
public interface IShooter {

	public void setBulletSpeed(float speed);

	public float getBulletSpeed();

	public void shoot(Vector2 from, Vector2 to);
	public void shoot(SpaceObject owner, Vector2 from, Vector2 to);
	public void shoot(SpaceObject owner, Vector2 to);
	public void shoot(Vector2 from, Vector2 to, float bulletSpeed);

	public ArrayList<Bullet> getBullets();
}
