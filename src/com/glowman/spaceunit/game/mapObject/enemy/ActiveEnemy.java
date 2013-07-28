package com.glowman.spaceunit.game.mapObject.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 */
public class ActiveEnemy extends Enemy {

	protected Sprite _passiveImage;
	protected Sprite _activeImage;

	private boolean _isActiveMode;

	public ActiveEnemy(String enemyType, Sprite passiveImage, Sprite activeImage, boolean randomScale, boolean teleportOnBorder) {
		super(enemyType, passiveImage, randomScale, teleportOnBorder);
		_activeImage = activeImage;
		_passiveImage = passiveImage;
		_isActiveMode = false;
	}

	protected boolean isActiveMode() { return _isActiveMode; }

	public void changeToActiveMode() {
		super.setImage(_activeImage);
		_isActiveMode = true;
	}

	public void changeToPassiveMode() {
		super.setImage(_passiveImage);
		_isActiveMode = false;
	}
}
