package com.glowman.spaceunit.game.mapObject.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.glowman.spaceunit.Assets;

/**
 *
 */
public class Alien extends ActiveEnemy {

	public Alien() {
		super(new Sprite(Assets.alienPassive), new Sprite(Assets.alienActive), false, true);
	}

	@Override
	public void setRotationSpeed(float rotation) {/*TODO need something more cool then it*/ }

}
