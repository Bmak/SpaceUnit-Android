package com.glowman.spaceunit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.game.ability.Ability;
import com.glowman.spaceunit.game.ability.AbilityENUM;

/**
 *
 */
public class AbilityButton {
	private Sprite _bkg;
	private Sprite _view;
	private Ability _ability;
	private float _highlightTimeLeft;
	private float _highlightTotalTime;
	private boolean _highlightFlag;

	public AbilityButton(Ability ability) {
		_highlightFlag = false;
		_highlightTotalTime = 0.4f;
		_highlightTimeLeft = _highlightTotalTime;
		_bkg = new Sprite(Assets.abilBkg);
		if (ability.getType() == AbilityENUM.BLOW) {
			_view = new Sprite(Assets.blowAbil);
		}
		else if (ability.getType() == AbilityENUM.MULTIGUN) {
			_view = new Sprite(Assets.superShipAbil);
		}
		else {
			throw new Error("unknown ability : " + ability.toString());
		}
		_ability = ability;

	}

	public void setPosition(float x, float y) {
		_view.setPosition(x - _view.getWidth()/2, y - _view.getHeight()/2);
		_bkg.setPosition(x - _bkg.getWidth()/2, y - _bkg.getHeight()/2);
	}

	public void draw(SpriteBatch drawer, float interfaceAlpha) {
		_bkg.draw(drawer);
		_view.draw(drawer);
	}

	public Sprite getView() { return _view; }

	public void tick(float delta) {
		Color color = _view.getColor();
		float alpha = (_ability.getCooldown() - _ability.timeToReady()) / _ability.getCooldown();
		if (alpha != 1) {
			color.a= alpha;
		} else {
			if (_highlightFlag) {
				color.a = 1;
			} else {
				color.a = 0;
			}

			_highlightTimeLeft -= delta;
			if (_highlightTimeLeft <= 0) {
				_highlightFlag = !_highlightFlag;
				_highlightTimeLeft = _highlightTotalTime;
			}
		}
		_bkg.setColor(color);
	}

	public boolean isReady() { return _ability.isReady(); }
}
