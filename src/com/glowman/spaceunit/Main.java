package com.glowman.spaceunit;



import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.glowman.spaceunit.achievements.AchievementsConrol;
import com.glowman.spaceunit.core.FPSViewer;
import com.glowman.spaceunit.core.CameraHelper;
import com.glowman.spaceunit.core.CameraHelper.ViewportMode;
import com.glowman.spaceunit.core.ScreenControl;


public class Main extends Game {
	
	private OrthographicCamera _camera;
	
	@Override
	public void create () {
		Assets.load();
		AchievementsConrol.init();
		FPSViewer.init(false);
		
		_camera = CameraHelper.createCamera2(ViewportMode.STRETCH_TO_ASPECT, Assets.VIRTUAL_WIDTH, Assets.VIRTUAL_HEIGHT,1/*, Assets.pixelDensity*/);
		CoordinatesTranslator.init(_camera);
		
		ScreenControl.init(this, _camera);
		
		setScreen(ScreenControl.getScreen(ScreenControl.MAIN));
		
		SoundPlayer.playMusic(Assets.backMenuSound);
	}
	
	@Override
	public void render() {
		super.render();
	}
	
	@Override
	public void resume() {
		super.resume();
	}
	
	@Override
	public void dispose () {
		super.dispose();

		getScreen().dispose();
	}
	
}
