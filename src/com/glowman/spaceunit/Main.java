package com.glowman.spaceunit;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.glowman.spaceunit.core.FPSViewer;
import com.glowman.spaceunit.core.CameraHelper;
import com.glowman.spaceunit.core.CameraHelper.ViewportMode;
import com.glowman.spaceunit.game.core.ScreenControl;


public class Main extends Game {
	
	//public static FPSLogger FPS;
	
	private OrthographicCamera _camera;
	
	@Override
	public void create () {
		Assets.load();
		FPSViewer.init(true);
		
		_camera = CameraHelper.createCamera2(ViewportMode.STRETCH_TO_SCREEN, Assets.VIRTUAL_WIDTH, Assets.VIRTUAL_HEIGHT, Assets.pixelDensity);
		CoordinatesTranslator.init(_camera);
		
		ScreenControl.init(this, _camera);
		
		setScreen(ScreenControl.getScreen(ScreenControl.MAIN));
		
		//FPS = new FPSLogger();
	}
	
	@Override
	public void render() {
		super.render();
		//FPS.log();
	}
	
	@Override
	public void dispose () {
		super.dispose();

		getScreen().dispose();
	}

}
