package com.glowman.spaceunit.core;

/**
 *
 */
public class TouchEvent {
	public static final int TOUCH_DOWN = 0;
	public static final int TOUCH_UP = 1;
	public static final int TOUCH_DRAGGED = 2;

	public int type;
	public int x;
	public int y;
	public int pointer;

	public TouchEvent(int type, int x, int y, int pointer)
	{
		this.type = type;
		this.x = x;
		this.y = y;
		this.pointer = pointer;
	}

}
