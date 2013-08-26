package com.glowman.spaceunit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.game.ability.Ability;
import com.glowman.spaceunit.game.ability.AbilityENUM;

/**
 *
 */
public class AbilityButton {
	private Sprite _view;
	private Ability _ability;

	public AbilityButton(Ability ability) {

		if (ability.getType() == AbilityENUM.BLOW) {
			_view = new Sprite(Assets.abilityButton);
		}
		else if (ability.getType() == AbilityENUM.MULTIGUN) {
			_view = new Sprite(Assets.abilityButton);
		}
		else {
			throw new Error("unknown ability : " + ability.toString());
		}
		_ability = ability;
	}

	public Sprite getView() { return _view; }

	public void tick(float delta) {
		Color color = _view.getColor();
		float red = (_ability.getCooldown() - _ability.timeToReady()) / _ability.getCooldown();
		color.r= red;
		_view.setColor(color);
	}

	public boolean isReady() { return _ability.isReady(); }
}
