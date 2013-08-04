package com.glowman.spaceunit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.Buffer;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;


public class Settings {
	public static final int RUN = 0;
	public static final int KILL = 1;

	public static final float FRAME_TIME = 1/60f;
	
	public static boolean soundEnabled = true;
	
	//TODO u will not release in v.1.0.0 :)
	public final static int[] run_highscores = new int[] { 5, 4, 3, 2, 1 };
	public final static int[] kill_highscores = new int[] { 50, 40, 30, 20, 10 };
	public final static String file = ".highscores";
	
	public static void load() {
		boolean isExtAvailable = Gdx.files.isExternalStorageAvailable();
		boolean isLocAvailable = Gdx.files.isLocalStorageAvailable();
		
		//Log.d("EXTERNAL", "storage path: " + isExtAvailable + Gdx.files.getExternalStoragePath());
		//Log.d("LOCAL", "storage path: " + isLocAvailable + Gdx.files.getLocalStoragePath());
		//Log.d("INPUT", "storage path: " + Gdx.files.getLocalStoragePath());
		
		
		FileHandle handle = Gdx.files.external(file);
		if (!handle.exists()) {
			try {
				handle.file().createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else  {
			BufferedReader reader = null;
			
			try {
				reader = new BufferedReader(new InputStreamReader(handle.read()));
				soundEnabled = Boolean.parseBoolean(reader.readLine());
				for(int i = 0; i < 5; i++) {
					run_highscores[i] = Integer.parseInt(reader.readLine());
				}
				for(int i = 0; i < 5; i++) {
					kill_highscores[i] = Integer.parseInt(reader.readLine());
				}
			} catch(IOException e) {
				// :( Не страшно, у нас есть значения по умолчанию
			} catch (NumberFormatException e) {
				// :/ Не страшно, опять же воспользуемся стандартными значениями
			} finally {
				try {
					if (reader != null)
						reader.close();
					} catch (IOException e) {
				}
			}
		}
	}
	
	public static void save() {
		FileHandle handle = Gdx.files.external(file);
		BufferedWriter out = null;
		try {
			handle.file().createNewFile();
			handle.file().setWritable(true);
			out = new BufferedWriter(new OutputStreamWriter(handle.write(true)));
			
			out.write(Boolean.toString(soundEnabled));
			out.write("\n");
			for(int i = 0; i < 5; i++) {
				out.write(Integer.toString(run_highscores[i]));
				out.write("\n");
			}
			for(int i = 0; i < 5; i++) {
				out.write(Integer.toString(kill_highscores[i]));
				out.write("\n");
			}
		} catch (IOException e) {
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
			}
		}
	}
	
	public static void addScore(int score, int type) {
		int[] top = new int[5];
		switch(type) {
			case RUN:
				top = run_highscores;
				break;
			case KILL:
				top = kill_highscores;
				break;
		}
		
		
		for(int i=0; i < 5; i++) {
			if(top[i] < score) {
				for(int j= 4; j > i; j--) {
					top[j] = top[j - 1];
				}
				top[i] = score;
				break;
			}
		}
	}
	
}
