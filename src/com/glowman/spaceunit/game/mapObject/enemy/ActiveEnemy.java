package com.glowman.spaceunit.game.mapObject.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 */
public class ActiveEnemy extends Enemy {

	protected Sprite _passiveImage;
	protected Sprite _activeImage;

	private boolean _isActiveMode;

	public ActiveEnemy(Sprite passiveImage, Sprite activeImage, boolean randomScale, boolean teleportOnBorder) {
		super(passiveImage, randomScale, teleportOnBorder);
		_activeImage = activeImage;
		_passiveImage = passiveImage;
		_isActiveMode = false;
	}

	protected boolean isActiveMode() { return _isActiveMode; }

	protected void changeToActiveMode() {
		super.setImage(_activeImage);
		_isActiveMode = true;
	}

	protected void changeToPassiveMode() {
		super.setImage(_passiveImage);
		_isActiveMode = false;
	}
}
