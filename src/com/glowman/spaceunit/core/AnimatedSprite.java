package com.glowman.spaceunit.core;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 *
 */
public class AnimatedSprite extends Sprite {
	private Animation _animation;
	private float _stateTime;
	private float _maxStateTime;
	private TextureRegion _currentTexture;

	public AnimatedSprite(Array<? extends TextureRegion> textures, float frameDuration){
		super();
		_animation = new Animation(frameDuration, textures);
		_stateTime = 0;
		_maxStateTime = textures.size * frameDuration;

		TextureRegion textureRegion = _animation.getKeyFrame(_stateTime);
		super.setRegion(textureRegion);
		super.setSize(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
		super.setOrigin(super.getWidth() / 2, super.getHeight() / 2);
	}

	public AnimatedSprite(Array<? extends TextureRegion> textures) {
		this(textures, 0.02f);
	}

	public void tick(float delta) {
		_stateTime += delta;
	}

	public boolean isAnimationFinished()
	{
		return _animation.isAnimationFinished(_stateTime);
	}

	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		_currentTexture = _animation.getKeyFrame(_stateTime);
		super.setRegion(_currentTexture);
		super.draw(spriteBatch);
	}
}
