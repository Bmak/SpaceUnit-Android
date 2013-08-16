package com.glowman.spaceunit.game;

import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.game.mapObject.Bullet;
import com.glowman.spaceunit.game.mapObject.SpaceObject;

import java.util.ArrayList;

/**
 *
 */
public interface IShooter {

	void tick(float delta);
	void shoot(Vector2 from, Vector2 to);
	void shoot(SpaceObject owner, Vector2 from, Vector2 to, int bulletType);
	void shoot(SpaceObject owner, Vector2 to, int bulletType);

	ArrayList<Bullet> getBullets();
}
