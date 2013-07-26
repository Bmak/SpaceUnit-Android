package com.glowman.spaceunit.game.mapObject;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 */
public class Bullet {

	private float _speed;
	private float _xCoef;
	private float _yCoef;
	private Sprite _view;

	public Bullet(Sprite view, float speed, float xCoef, float yCoef)
	{
		_view = view;
		_speed = speed;
		_xCoef = xCoef;
		_yCoef = yCoef;
	}

	public void move()
	{
		float x = _view.getX() + _speed * _xCoef;
		float y = _view.getY() + _speed + _yCoef;
		_view.setPosition(x, y);
	}

	public Sprite getView() { return _view; }
}
