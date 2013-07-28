package com.glowman.spaceunit.game.behavior;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.game.SpeedFactory;
import com.glowman.spaceunit.game.mapObject.MovingSpaceObject;


public class AsteroidsBehavior {
	
	private int _numStartEnemies;
	private List<MovingSpaceObject> _spaceObjects;
	private SpriteBatch _spriteBatch;
	

	public AsteroidsBehavior(int numStartEnemies, SpriteBatch spriteBatch) {
		_numStartEnemies = numStartEnemies;
		_spriteBatch = spriteBatch;
		
		_spaceObjects = new ArrayList<MovingSpaceObject>();
		Vector2 screenSize = new Vector2(Assets.VIRTUAL_WIDTH, Assets.VIRTUAL_HEIGHT);
		for (int i = 0; i < _numStartEnemies; i++) {
			Sprite image = new Sprite(Assets.soImages[Math.round((float)Math.random())]);
			MovingSpaceObject spaceObject = this.createRandomSpaceObject(image);
			_spaceObjects.add(spaceObject);
		}
	}
	
	public void tick(float deltaTime) {
		for (MovingSpaceObject spaceObject : _spaceObjects) {
			spaceObject.tick(deltaTime);
			spaceObject.getImage().draw(_spriteBatch);
		}
	}

	private MovingSpaceObject createRandomSpaceObject(Sprite image) {
		MovingSpaceObject spaceObject = new MovingSpaceObject(image, true, true);
		spaceObject.setRandomBorderPosition();
		spaceObject.setGeneralSpeed(SpeedFactory.getSpeed(spaceObject, -1));
		spaceObject.setRotationSpeed(5 * ((float)Math.random() * 2 - 1)); //TODO kick it out
		spaceObject.moveTo((float)Math.random() * Assets.VIRTUAL_WIDTH,
				(float)Math.random() * Assets.VIRTUAL_HEIGHT);
		return spaceObject;
	}

}
