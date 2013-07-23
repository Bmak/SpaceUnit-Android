package com.glowman.spaceunit.game.behavior;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.game.mapObject.Enemy;
import com.glowman.spaceunit.game.mapObject.Meteor;


public class AsteroidsBehavior {
	
	private float _koeff;
	private float _changeTime;
	private int _numStartEnemies;
	private List<Enemy> _enemies;
	private SpriteBatch _spriteBatch;
	
	private Sprite[] types = { Assets.asteroid, Assets.meteor };
	
	public AsteroidsBehavior(float koeff, int numStartEnemies, SpriteBatch spriteBatch) {
		_koeff = koeff;
		_numStartEnemies = numStartEnemies;
		_spriteBatch = spriteBatch;
		
		_enemies = new ArrayList<Enemy>();
		Vector2 screenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		for (int i = 0; i < _numStartEnemies; i++) {
			Sprite image = types[Math.round((float)Math.random())];
			image.setScale(0.5f);
			Meteor enemy = new Meteor(image, screenSize);
			//enemy.setRandomBorderPosition();
			enemy.setRandomPosition();
			enemy.setRandomBehaviour();
			//enemy.setRandomGeneralSpeed();
			_enemies.add(enemy);
		}
	}
	
	public void updateKoeff(float koeff) {
		_koeff = koeff;
	}
	
	public void tick(float deltaTime) {
		for (int i = 0; i < _enemies.size(); i++) {
			Meteor enemy = (Meteor)_enemies.get(i);
			enemy.tick(deltaTime);
			enemy.getImage().draw(_spriteBatch);
		}
	}
}
