package com.glowman.spaceunit.game.behavior;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.game.mapObject.MovingSpaceObject;


public class AsteroidsBehavior {
	
	private int _numStartEnemies;
	private List<MovingSpaceObject> _spaceObjects;
	private SpriteBatch _spriteBatch;
	

	public AsteroidsBehavior(int numStartEnemies, SpriteBatch spriteBatch) {
		_numStartEnemies = numStartEnemies;
		_spriteBatch = spriteBatch;
		
		_spaceObjects = new ArrayList<MovingSpaceObject>();
		Vector2 screenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		for (int i = 0; i < _numStartEnemies; i++) {
			Sprite image = new Sprite(Assets.soImages[Math.round((float)Math.random())]);
			//image.setScale(0.5f);
			MovingSpaceObject spaceObject = new MovingSpaceObject(image, screenSize, true, true);
			spaceObject.setRandomPosition();
			spaceObject.setRandomBehaviour();
			_spaceObjects.add(spaceObject);

		}
	}
	
	public void tick(float deltaTime) {
		for (MovingSpaceObject spaceObject : _spaceObjects) {
			spaceObject.tick(deltaTime);
			spaceObject.getImage().draw(_spriteBatch);
		}
	}
}
