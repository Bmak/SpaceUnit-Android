package com.glowman.spaceunit;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.glowman.spaceunit.game.core.CameraHelper;

/**
 *
 */
public class CoordinatesTranslator {

	private static OrthographicCamera _camera;

	public static void init(OrthographicCamera camera)
	{
		_camera = camera;
	}

	public static Vector3 toVirtualView(float x, float y)
	{
		if (_camera == null) { throw new Error("camera not inited!"); }
		return CameraHelper.screenToViewport(_camera, x, y);
	}

}
