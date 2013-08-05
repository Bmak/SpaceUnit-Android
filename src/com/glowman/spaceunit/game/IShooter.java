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

	void setBulletSpeed(float speed);

	float getBulletSpeed();

	void tick(float delta);
	void shoot(Vector2 from, Vector2 to);
	void shoot(SpaceObject owner, Vector2 from, Vector2 to);
	void shoot(SpaceObject owner, Vector2 to);
	void shoot(Vector2 from, Vector2 to, float bulletSpeed);

	ArrayList<Bullet> getBullets();
}
