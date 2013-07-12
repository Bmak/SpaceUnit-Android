package com.glowman.android.framework;

import com.glowman.android.framework.Graphics.PixmapFormat;

public interface Pixmap {
	public int getWidth();
	
	public int getHeight();

	public void setScale(int scale);
	public int getScale();
	
	public PixmapFormat getFormat();
	
	public void dispose();
}
