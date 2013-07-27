package com.glowman.spaceunit.game.mapObject.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.glowman.spaceunit.Assets;

/**
 *
 */
public class EnemyFactory {

	//TODO game balance here
	public static Enemy createEnemy() {
		Enemy result;
		double random = Math.random();
		if (random < .33) {
			Sprite image = new Sprite(Assets.soImages[Math.round((float)Math.random())]);
			result = new Enemy(image, true);
		} else if (random < .63) {
			result = new Mine();
		} else {
			result = new Alien();
		}

		return result;
	}
}
