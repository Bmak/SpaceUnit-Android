package com.glowman.spaceunit.game.core;

/**
 *
 */
public class TouchEvent {
	static final int TOUCH_DOWN = 0;
	static final int TOUCH_UP = 1;
	static final int TOUCH_DRAGGED = 2;

	public int type;
	public int x;
	public int y;
	public int pointer;
}
