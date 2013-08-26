package com.glowman.spaceunit.game.ability;

import com.glowman.spaceunit.game.mapObject.hero.Ship;
import com.glowman.spaceunit.game.mapObject.impact.IImpactMaker;

/**
 *
 */
public abstract class Ability {
	private AbilityENUM _abilityType;
	private float _cooldown;
	private float _timeLeft;
	private boolean _ready;

	public static Ability create(AbilityENUM ability, Ship heroShip, IImpactMaker impactMaker) {
		Ability result = null;
		if (ability == AbilityENUM.BLOW) {
			if (impactMaker == null) { throw new Error("impact maker cant be null for ability : " + ability); }
			result = new BlowAbility(heroShip, impactMaker);
		} else if (ability == AbilityENUM.MULTIGUN) {
			result = new MultigunAbility(heroShip);
		}
		return result;
	}
	public static Ability create(AbilityENUM ability, Ship heroShip) {
		return create(ability, heroShip, null);
	}


	public Ability(AbilityENUM abilityType, float cooldown) {
		_cooldown = cooldown;
		_abilityType = abilityType;
		_timeLeft = 0;
	}

	public AbilityENUM getType() { return _abilityType; }

	public boolean isReady() { return _ready; }

	public float timeToReady() { return _timeLeft; }

	public float getCooldown() { return _cooldown; }

	public void activate() {
		if (!_ready) { throw new Error("cant activate ability. dont ready"); }
		_ready = false;
		_timeLeft = _cooldown;
	}

	public void deactivate() {

	}

	public void tick(float delta) {
		if (!_ready) {
			_timeLeft -= delta;
			if (_timeLeft <= 0) {
				_ready = true;
				_timeLeft = 0;
			}
		}
	}

}
