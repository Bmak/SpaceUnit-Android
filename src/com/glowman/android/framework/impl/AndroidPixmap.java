package com.glowman.android.framework.impl;

import android.graphics.Bitmap;

import com.glowman.android.framework.Graphics.PixmapFormat;
import com.glowman.android.framework.Pixmap;

public class AndroidPixmap implements Pixmap {
	Bitmap bitmap;
	PixmapFormat format;

	private int _scale;
	
	public AndroidPixmap(Bitmap bitmap, PixmapFormat format) {
		this.bitmap = bitmap;
		this.format = format;
		this._scale = 1;
	}
	
	@Override
	public int getWidth() {
		return bitmap.getWidth();
	}
	@Override
	public int getHeight() {
		return bitmap.getHeight();
	}
	@Override
	public PixmapFormat getFormat() {
		return format;
	}
	@Override
	public void dispose() {
		bitmap.recycle();
	}

	@Override
	public void setScale(int scale) {
		if (_scale != scale)
		{
			_scale = scale;
			Bitmap.createScaledBitmap(bitmap, _scale, _scale, true);
		}
	}

	@Override
	public int getScale() { return _scale; }
}
