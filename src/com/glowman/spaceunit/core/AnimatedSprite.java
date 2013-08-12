package com.glowman.spaceunit.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 *
 */
public class AnimatedSprite extends Sprite {
	protected Animation _animation;
	private float _stateTime;
	protected float _maxStateTime;
	protected TextureRegion _currentTexture;
	private int _playMode;

	public AnimatedSprite(Array<? extends TextureRegion> textures, float frameDuration, int playMode){
		super();
		_animation = new Animation(frameDuration, textures);
		_stateTime = 0;
		_maxStateTime = textures.size * frameDuration;
		_playMode = playMode;

		TextureRegion textureRegion = _animation.getKeyFrame(_stateTime);
		super.setRegion(textureRegion);
		super.setSize(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
		super.setOrigin(super.getWidth() / 2, super.getHeight() / 2);
		_animation.setPlayMode(_playMode);
	}

	public AnimatedSprite(Array<? extends TextureRegion> textures) {
		this(textures, 0.02f, Animation.NORMAL);
	}

	public void tick(float delta) {
		_stateTime += delta;
	}
	
	public TextureRegion getCurrentRegion() {
		return _animation.getKeyFrame(_stateTime);
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
