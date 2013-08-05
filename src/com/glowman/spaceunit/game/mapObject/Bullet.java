package com.glowman.spaceunit.game.mapObject;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 */
public class Bullet extends MovingSpaceObject {

	private Sprite _view;
	private SpaceObject _owner;

	public Bullet(Sprite view, SpaceObject owner) {
		super(view, false, BORDER_BEHAVIOUR.NONE);
		_view = view;
		_owner = owner;
	}

	public SpaceObject getOwner() { return _owner; }

	public Bullet(Sprite view)
	{
		this(view, null);
	}

	public Sprite getView() { return _view; }
}
