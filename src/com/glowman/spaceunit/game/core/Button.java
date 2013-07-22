package com.glowman.spaceunit.game.core;

import android.util.Log;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 */
public class Button {
	private final Sprite _normal;
	private final Sprite _clicked;
	private Sprite _currentView;

	public Button(TextureRegion normal, TextureRegion clicked)
	{
		_normal = new Sprite(normal);
		_clicked = new Sprite(clicked);
		_currentView = _normal;
	}

	public Sprite getView() { return _currentView; }

	public void setClickedMode()
	{
		_currentView = _clicked;
	}

	public void setNormalMode()
	{
		_currentView = _normal;
	}

	public void draw(SpriteBatch spriteBatch)
	{
		_currentView.draw(spriteBatch);
	}

	public void setScale(float scale)
	{
		_normal.setScale(scale);
		_clicked.setScale(scale);

		updateXY();

	}

	public void setX(float x)
	{
		_normal.setX(x);
		_clicked.setX(x);
	}
	public void setY(float y)
	{
		/*
		Log.d("hz", "scale now : " + _normal.getScaleY());
		float scaledDelta = (_normal.getHeight() - _normal.getHeight() * _normal.getScaleX());
		_normal.setY(y - scaledDelta);
		//scaledDelta = (_clicked.getHeight() - _clicked.getHeight() * _clicked.getScaleX());
		_clicked.setY(y + (_normal.getHeight() - _clicked.getHeight())- scaledDelta);
		
		*/
		
		_normal.setY(y);
		
		Log.d("hz", "scale now : " + _normal.getScaleY());
		Log.d("wtf", "size now : " + _clicked.getHeight() + " " + _normal.getHeight());
		
		float otherY = y - (_clicked.getHeight() - _normal.getHeight())*_normal.getScaleY();
		
		_clicked.setY(otherY);
	}

	private void updateXY()
	{
		this.setY(_normal.getY());
	}

}
