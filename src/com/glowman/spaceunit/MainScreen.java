package com.glowman.spaceunit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import android.util.Log;



public class MainScreen implements Screen {
	
	float MENU_WIDTH = Gdx.graphics.getWidth();
	float MENU_HEIGHT = Gdx.graphics.getHeight();
	
	private Game _game;
	
	SpriteBatch spriteBatch;
	private OrthographicCamera menuCam;
	//private SimpleButton playButton;
	//private SimpleButton scoresButton;
	
	private Sprite _playBtn;
	private Sprite _bkg;
	private Vector3 _touchPoint;
	private boolean _wasTouched;


	public MainScreen(Game game) {
		_game = game;
		
		_touchPoint = new Vector3();
		
		_bkg = Assets.bkg1;
		_playBtn = Assets.playBtnRun1;
		
		menuCam = new OrthographicCamera(MENU_WIDTH, MENU_HEIGHT);
		menuCam.position.set(MENU_WIDTH / 2f, MENU_HEIGHT / 2f, 0);
		menuCam.update();
		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(menuCam.combined);
		
		Log.d("SIZE", "size: " + MENU_WIDTH + " / " + MENU_HEIGHT);
		
	}

	@Override
	public void resize(int width, int height) {
		MENU_WIDTH = (float) width;
		MENU_HEIGHT = (float) height;
		
		Log.d("RESIZE", "REsize: " + MENU_WIDTH + " / " + MENU_HEIGHT);
		
		//TODO refact this
		Assets.playBtnRun1.setScale(1.5f);
		Assets.playBtnRun2.setScale(1.5f);
		
		Assets.playBtnRun1.setX((MENU_WIDTH - Assets.playBtnRun1.getWidth())/2);
		Assets.playBtnRun1.setY((MENU_HEIGHT - Assets.playBtnRun1.getHeight())/2);
		Assets.playBtnRun2.setX((MENU_WIDTH - Assets.playBtnRun2.getWidth())/2);
		Assets.playBtnRun2.setY((MENU_HEIGHT - Assets.playBtnRun2.getHeight())/2);
		
		//TODO this shit cuz wrong picture
		//_bkg.setRotation(90);
		_bkg.setSize(MENU_WIDTH, MENU_HEIGHT);
	}

	@Override
	public void hide() {}

	@Override
	public void show() {}

	@Override
	public void render(float deltaTime) {
		_playBtn = Assets.playBtnRun1;
		if (Gdx.input.isTouched()) {
			_touchPoint.x = Gdx.input.getX();
			_touchPoint.y = Gdx.input.getY();
			
			if (_playBtn.getBoundingRectangle().contains(_touchPoint.x, _touchPoint.y)) {
				_playBtn = Assets.playBtnRun2;
			}
		}
		
		
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		spriteBatch.begin();
		
		_bkg.draw(spriteBatch);
		_playBtn.draw(spriteBatch);
		
		spriteBatch.end();
		
		
		
		//Log.d("hz", "render!!!");
//		List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
//		int len = touchEvents.size();
//
//		for(int i = 0; i < len; i++) {
//			Input.TouchEvent event = touchEvents.get(i);
//
//			if (event.type == Input.TouchEvent.TOUCH_DRAGGED)
//			{
//				Log.d("hz", "touch dragged!");
//			}
//
//
//			if (OverlapTester.pointInCircle(_buttonModel, new Vector2(event.x, event.y))) {
//				if(event.type == Input.TouchEvent.TOUCH_DOWN) {
//					this.updateButtonColor(TOUCH_BTN_COLOR);
//				} else if(event.type == Input.TouchEvent.TOUCH_UP) {
//					this.updateButtonColor(DEFAULT_BTN_COLOR);
//					Log.d("hz", "try to change screen, TADASH!");
//					game.setScreen(new GameScreen(game));
//				}
//			} else {
//				this.updateButtonColor(DEFAULT_BTN_COLOR);
//			}
//		}
	}

	@Override
	public void pause() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void resume() {
//		this.drawButton();
	}

	@Override
	public void dispose() {
		//_g.clear(Color.WHITE);
	}

//	private void updateButtonColor(final int color) {
//		this.drawButtonCircle(color);
//	}
//
//	private void drawButton() {
//		this.drawButtonCircle(DEFAULT_BTN_COLOR);
//		_g.drawText(BUTTON_TEXT, (int) _buttonModel.center.x, (int) _buttonModel.center.y, 25, Color.BLACK);
//	}
//
//	private void drawButtonCircle(final int circleColor)
//	{
//		_g.drawCirlce(_buttonModel.center.x, _buttonModel.center.y, _buttonModel.radius, circleColor);
//	}

}
