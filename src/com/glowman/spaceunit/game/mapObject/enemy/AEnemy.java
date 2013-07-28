package com.glowman.spaceunit.game.mapObject.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.glowman.spaceunit.game.mapObject.SpaceObject;
import com.glowman.spaceunit.game.mapObject.MovingSpaceObject;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.AEnemyBehaviour;

import java.util.ArrayList;

/**
 *
 */
abstract public class AEnemy extends MovingSpaceObject {
	protected SpaceObject _target;
	private ArrayList<AEnemyBehaviour> _behaviours;

	public AEnemy(Sprite image, boolean randomScale, boolean teleportOnBorder)
	{
		super(image, randomScale, teleportOnBorder);
	}

	@Override
	public void tick(float delta) {
		super.tick(delta);
		if (_behaviours != null) {
			for (AEnemyBehaviour behaviour : _behaviours) {
				behaviour.tick(delta);
			}
		}
	}

	/**
	 *
	 * @param behaviour
	 * @throws Error "behaviour already exists"
	 */
	public void addBehaviour(AEnemyBehaviour behaviour) throws Error {
		if (_behaviours == null) { _behaviours = new ArrayList<AEnemyBehaviour>(); }
		if (this.behaviourExists(behaviour.getName())) {
			throw new Error("behaviour already exists");
		}
		_behaviours.add(behaviour);
	}

	/**
	 *
	 * @param behaviourName
	 * @throws Error "behaviour " + behaviourName + " not found in behaviours"
	 */
	public void removeBehaviour(String behaviourName) {
		boolean behaviourFound = false;
		if (_behaviours != null) {
			for (AEnemyBehaviour behaviour : _behaviours) {
				if (behaviour.getName() == behaviourName) {
					_behaviours.remove(behaviour);
					behaviourFound = true;
					break;
				}
			}
		}
		if (!behaviourFound) {
			throw new Error("behaviour " + behaviourName + " not found in behaviours");
		}
	}

	public boolean behaviourExists(String behaviourName) {
		boolean result = false;
		for (AEnemyBehaviour behaviour : _behaviours) {
			if (behaviour.getName() == behaviourName) {
				result = true;
				break;
			}
		}
		return result;
	}

	public void setTarget(SpaceObject target) {
		_target = target;
	}


}
