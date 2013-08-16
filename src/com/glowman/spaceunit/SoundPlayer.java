package com.glowman.spaceunit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.ArrayList;

/**
 *
 */
public class SoundPlayer {

	private static boolean _soundOn = true;

	private static Music _playingMusic = null;

	public static void soundOn() {
		_soundOn = true;
		if (_playingMusic != null) {
			_playingMusic.play();
		}
	}

	public static void soundOff() {
		_soundOn = false;
		if (_playingMusic != null) {
			try {
				_playingMusic.stop();
			}
			catch (Error e) {
				Gdx.app.log("hz", "error while playing music stopping, something with player in Android music..");
			}
		}
	}

	public static void playSound(Sound sound, float volume) {
		if (_soundOn) {
			sound.play(volume);
		}
	}
	public static void playSound(Sound sound) {
		playSound(sound, 1f);
	}

	public static void playMusic(Music music, float volume) {
		if (_soundOn) {
			music.setVolume(volume);
			music.play();
			_playingMusic = music;
		}
	}
	public static void playMusic(Music music) {
		playMusic(music, 1f);
	}

	public static void stopMusic(Music music) {
		music.stop();
	}
}
