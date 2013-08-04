package com.glowman.spaceunit.menu.behavior;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.game.animation.BlowAnimation;
import com.glowman.spaceunit.game.animation.BlowController;
import com.glowman.spaceunit.game.balance.SpeedCollector;
import com.glowman.spaceunit.game.mapObject.MovingSpaceObject;


public class AsteroidsBehavior {
	
	private int _numStartEnemies;
	private List<MovingSpaceObject> _spaceObjects;
	private SpriteBatch _spriteBatch;
	private BlowController _blowControl;

	public AsteroidsBehavior(int numStartEnemies, SpriteBatch spriteBatch) {
		_numStartEnemies = numStartEnemies;
		_spriteBatch = spriteBatch;
		
		_blowControl = new BlowController();
		
		_spaceObjects = new ArrayList<MovingSpaceObject>();
		for (int i = 0; i < _numStartEnemies; i++) {
			Sprite image = new Sprite(Assets.soImages[Math.round((float)Math.random())]);
			MovingSpaceObject spaceObject = this.createRandomSpaceObject(image);
			_spaceObjects.add(spaceObject);
		}
	}
	
	public List<MovingSpaceObject> getSpaceObjects() { return _spaceObjects; }
	
	public void tick(float deltaTime) {
		for (MovingSpaceObject spaceObject : _spaceObjects) {
			spaceObject.tick(deltaTime);
			spaceObject.getImage().draw(_spriteBatch);
		}
		if (_blowControl.getBlows() != null) {
			_blowControl.tick(deltaTime);
			for (BlowAnimation blow : _blowControl.getBlows()) {
				blow.tick(deltaTime);
				blow.draw(_spriteBatch);
			}
		}
	}
	
	public void setBlow(MovingSpaceObject spaceObject) {
		_blowControl.makeBlow(spaceObject.getImage().getX() + spaceObject.getWidth()/2,
							spaceObject.getImage().getY() + spaceObject.getWidth()/2,
							spaceObject.getWidth());
		//Sprite image = new Sprite(Assets.soImages[Math.round((float)Math.random())]);
		//spaceObject = this.createRandomSpaceObject(image);
		spaceObject.setRandomBorderPosition();
	}
	
	private MovingSpaceObject createRandomSpaceObject(Sprite image) {
		MovingSpaceObject spaceObject = new MovingSpaceObject(image, true, true);
		spaceObject.setRandomBorderPosition();
		spaceObject.setGeneralSpeed(SpeedCollector.getMenuAsteroidSpeed());
		spaceObject.setRotationSpeed(5 * ((float)Math.random() * 2 - 1)); //TODO kick it out
		spaceObject.moveTo((float)Math.random() * Assets.VIRTUAL_WIDTH,
				(float)Math.random() * Assets.VIRTUAL_HEIGHT);
		return spaceObject;
	}

}
