package com.glowman.spaceunit.credits;

import android.util.Log;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureAdapter;
import com.badlogic.gdx.math.Vector2;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.core.Button;
import com.glowman.spaceunit.core.FPSViewer;
import com.glowman.spaceunit.core.ScreenControl;
import com.glowman.spaceunit.menu.behavior.AsteroidsBehavior;

public class CreditsScreen extends GestureAdapter implements Screen {
	
	private final Game _game;
	private final SpriteBatch _drawer;
	private OrthographicCamera _camera;
	
	private Button _backBtn;
	
	private Sprite _credits;
	private final float START_POS_Y;
	private final float FINAL_POS_Y;
	
	private final String TITLE_TEXT = "SPACE UNIT";
	private Sprite _title;
	private final float _startX;
	private final float _startY;
	private Vector2 _posTitle;
	private float _posY;
	
	private final int DEF_SPEED = 50;
	private float _speed;
	
	private CreditsListener _creditsListener;
	private AsteroidsBehavior _asterBehavior;
	private AsteroidsBehavior _meteorBehavior;
	
	private Boolean _isPan = false;
	
	
	public CreditsScreen(Game game, OrthographicCamera camera)
	{
		_game = game;
		_camera = camera;
		_drawer = new SpriteBatch();
		_drawer.setProjectionMatrix(_camera.combined);
		
		_title = new Sprite(Assets.title);
		_startX = (Assets.VIRTUAL_WIDTH - _title.getWidth())/2;
		_startY = (Assets.VIRTUAL_HEIGHT - _title.getHeight())/2;
		
		_credits = new Sprite(Assets.credits);
		_credits.setSize(Assets.creditsWidth, Assets.credtisHeight);
		
		_backBtn = new Button(Assets.getBackBtnRegin(1), Assets.getBackBtnRegin(2));
		_backBtn.setSize(Assets.backBtnWidth, Assets.backBtnHeight);
		_backBtn.setX(_backBtn.getWidth()/5);
		_backBtn.setY(_backBtn.getHeight()/5);
		
		START_POS_Y = Assets.VIRTUAL_HEIGHT - _credits.getHeight()*1.5f;
		FINAL_POS_Y = _credits.getHeight()/10;
		
		_asterBehavior = new AsteroidsBehavior(10, _drawer);
		_meteorBehavior = new AsteroidsBehavior(10, _drawer);
		
		_creditsListener = new CreditsListener(_game, this);
		_creditsListener.addButton(_backBtn, ScreenControl.CREDITS);
		_creditsListener.addBehavior(_asterBehavior);
		_creditsListener.addBehavior(_meteorBehavior);
	}
	
	@Override
	public void render(float delta) {
		this.clear();
		_drawer.begin();
		
		_asterBehavior.tick(delta);
		
		//_title.draw(_drawer, TITLE_TEXT, _posTitle.x, _posTitle.y);
		_title.setY(_posTitle.y);
		_title.draw(_drawer);
		_credits.draw(_drawer);
		updatePositions(delta);
		
		_meteorBehavior.tick(delta);
		
		_backBtn.draw(_drawer);
		FPSViewer.draw(_drawer);
		_drawer.end();
	}
	
	private void updatePositions(float delta) {
		if (_isPan) { return; }
		
		if (_speed > DEF_SPEED) {
			_speed -= DEF_SPEED;
		} else if (_speed < DEF_SPEED) {
			_speed += DEF_SPEED;
		} else if (_speed > DEF_SPEED+0.2f || _speed < DEF_SPEED - 0.2f) {
			_speed = DEF_SPEED;
		}
		
		if (checkOutOfBounds()) { return; }
		
		_posTitle.y += _speed*delta;
		_posY += _speed*delta;
		_credits.setY(_posY);
	}
	
	private boolean checkOutOfBounds() {
		if (_credits.getY() > FINAL_POS_Y && _speed > 0) { 
			//_credits.setY(FINAL_POS_Y);
			_speed = 0;
			_isPan = false;
			return true;
		}
		if (_credits.getY() < START_POS_Y  && _speed < 0) { 
			//_credits.setY(START_POS_Y);
			_speed = 0;
			_isPan = false;
			return true;
		}
		return false;
	}
	
	private boolean checkOut() {
		if (_credits.getY() > FINAL_POS_Y) { 
			//_credits.setY(FINAL_POS_Y);
			_speed = 0;
			_isPan = false;
			return true;
		}
		if (_credits.getY() < START_POS_Y) { 
			//_credits.setY(START_POS_Y);
			_speed = 0;
			_isPan = false;
			return true;
		}
		return false;
	}
	
	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		if (checkOutOfBounds()) { return super.fling(velocityX, velocityY, button); }
		
		
		Log.d("CREDITS", "FLIP FLIP FLIP " + velocityX/Assets.pixelDensity + " " + velocityY/Assets.pixelDensity);
		_speed += -Math.min(700, velocityY/(2*Assets.pixelDensity));
		_isPan = false;
		return super.fling(velocityX, velocityY, button);
	}
	
	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		if (checkOut()) { return super.pan(x, y, deltaX, deltaY); }
		
		Log.d("CREDITS", "pan pan pan " + deltaX + " " + deltaY);
		//TODO dodelat'!!!
		
		
		_isPan = true;
		_posTitle.y += -deltaY/Assets.pixelDensity;
		_posY += -deltaY/Assets.pixelDensity;
		_credits.setY(_posY);
		
		return super.pan(x, y, deltaX, deltaY);
	}
	
	
	@Override
	public void show() {
		Log.d("CREDITS", "screen pause");
		
		Gdx.input.setInputProcessor(_creditsListener);
		
		_speed = DEF_SPEED;
		_isPan = false;
		
		_credits.setX((Assets.VIRTUAL_WIDTH - _credits.getWidth())/2);
		_credits.setY(START_POS_Y);
		_posY = _credits.getY();
		
		_posTitle = new Vector2(_startX, _startY);
		_title.setX(_posTitle.x);
	}
	
	@Override
	public void hide() {
		Log.d("CREDITS", "screen hide");
		this.clear();
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		Log.d("CREDITS", "screen pause");
	}

	@Override
	public void resume() { 
		Log.d("CREDITS", "screen pause");
	}
	
	@Override
	public void resize(int width, int height) { }
	
	@Override
	public void dispose() {
		this.clear();
	}

	private void clear() {
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}

}
