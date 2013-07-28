package com.glowman.spaceunit.game.mapObject;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 */
public class Bullet extends MovingSpaceObject {

	private float _speed;
	private Sprite _view;

	public Bullet(Sprite view, float speed)
	{
		super(view, false, false);
		_view = view;
		_speed = speed;
		super.setGeneralSpeed(speed);
	}

	public Sprite getView() { return _view; }
}
