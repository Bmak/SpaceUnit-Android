package com.glowman.spaceunit.game.mapObject.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.glowman.spaceunit.game.mapObject.SpaceObject;
import com.glowman.spaceunit.game.mapObject.MovingSpaceObject;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.EBehaviourENUM;
import com.glowman.spaceunit.game.mapObject.enemy.behaviour.core.AEnemyBehaviour;

import java.util.ArrayList;

/**
 *
 */
public class Enemy extends MovingSpaceObject {
	protected SpaceObject _target;

	private String _enemyType;
	private ArrayList<AEnemyBehaviour> _behaviours;

	public Enemy(String enemyType, Sprite image, boolean randomScale, BORDER_BEHAVIOUR borderBehaviour)
	{
		super(image, randomScale, borderBehaviour);
		_enemyType = enemyType;
	}

	public String getEnemyType() { return _enemyType; }

	@Override
	public void tick(float delta) {
		super.tick(delta);
		if (_behaviours != null) {
			ArrayList<AEnemyBehaviour> clonedBehaviours = new ArrayList<AEnemyBehaviour>();
			for (AEnemyBehaviour behaviour : _behaviours) {
				clonedBehaviours.add(behaviour);
			}

			for (AEnemyBehaviour behaviour : clonedBehaviours) {
				behaviour.tick(delta);
			}

			clonedBehaviours.clear();
		}
	}

	@Override
	public void setDead(){
		super.setDead();
		this.removeAllBehaviours();
	}

	/**
	 *
	 * @param behaviour
	 * @throws Error "behaviour already exists"
	 */
	public void addBehaviour(AEnemyBehaviour behaviour) throws Error {
		if (_behaviours == null) { _behaviours = new ArrayList<AEnemyBehaviour>(); }
		if (this.hasBehaviour(behaviour.getName())) {
			throw new Error("behaviour already exists");
		}
		_behaviours.add(behaviour);
		behaviour.start();
	}

	/**
	 *
	 */
	public void removeAllBehaviours() {
		if (_behaviours != null) {
			for (AEnemyBehaviour behaviour : _behaviours) {
				behaviour.stop();
			}
			_behaviours.clear();
		}
	}

	/**
	 *
	 * @param behaviourName
	 * @throws Error "behaviour " + behaviourName + " not found in behaviours"
	 */
	public void removeBehaviour(EBehaviourENUM behaviourName) {
		boolean behaviourFound = false;
		if (_behaviours != null) {
			for (AEnemyBehaviour behaviour : _behaviours) {
				if (behaviour.getName() == behaviourName) {
					_behaviours.remove(behaviour);
					behaviour.stop();
					behaviourFound = true;
					break;
				}
			}
		}
		if (!behaviourFound) {
			throw new Error("behaviour " + behaviourName + " not found in behaviours");
		}
	}

	public boolean hasBehaviour(EBehaviourENUM behaviourName) {
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
