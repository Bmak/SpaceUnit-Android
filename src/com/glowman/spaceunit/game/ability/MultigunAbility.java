package com.glowman.spaceunit.game.ability;

import com.badlogic.gdx.Gdx;
import com.glowman.spaceunit.game.mapObject.gun.AGun;
import com.glowman.spaceunit.game.mapObject.gun.MultiGun;
import com.glowman.spaceunit.game.mapObject.hero.HeroSkin;
import com.glowman.spaceunit.game.mapObject.hero.MultigunHeroSkin;
import com.glowman.spaceunit.game.mapObject.hero.Ship;

/**
 *
 */
public class MultigunAbility extends Ability {

	private Ship _heroShip;
	private AGun _heroGun;
	private HeroSkin _heroSkin;
	private final float ABIL_TIME = 5;
	private float _abilTimeLeft;
	private boolean _isActive;

	public MultigunAbility(Ship heroShip) {
		super(AbilityENUM.MULTIGUN, 20);
		_heroShip = heroShip;
		_isActive = false;
	}

	@Override
	public void tick(float delta) {
		super.tick(delta);
		if (_isActive) {
			_abilTimeLeft-= delta;
			if (_abilTimeLeft <= 0) {
				this.deactivate();
			}
			else if (_heroShip.getGun() != null) {
				if (_heroShip.getGun().isReloading()) {
					_heroShip.setRotationSpeed(4);
				} else {
					_heroShip.setRotationSpeed(0);
				}
			}
		}
	}

	@Override
	public void deactivate() {
		if (!_isActive) return;
		_isActive = false;
		_heroShip.setGun(_heroGun);
		_heroShip.setRotationSpeed(0);
		_heroShip.setSkin(_heroSkin);
	}


	@Override
	public void activate() {
		super.activate();
		_isActive = true;
		_abilTimeLeft = ABIL_TIME;
		_heroGun = _heroShip.getGun();
		_heroSkin = _heroShip.getSkin();
		_heroShip.setGun(new MultiGun());
		_heroShip.setSkin(new MultigunHeroSkin());
	}
}
