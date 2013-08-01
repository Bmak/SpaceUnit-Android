package com.glowman.spaceunit.data;

import com.glowman.spaceunit.MainAndroid;
import com.glowman.spaceunit.core.GPGSActivity;
import com.glowman.spaceunit.core.GameHelper;
import com.glowman.spaceunit.game.strategy.GameStrategy;
import com.google.android.gms.games.GamesClient;

public class GooglePlayData {
	
	private static final String TOP_RUN = "CgkI8evoqtYQEAIQAQ";
	private static final String TOP_KILL = "CgkI8evoqtYQEAIQAg";
	
	public static MainAndroid game;
	public static GameHelper gameHelper;
	public static GamesClient gamesClient;
	
	public static String getLeaderboardID(int gameType) {
		switch(gameType) {
			case GameStrategy.RUN_GAME:
				return TOP_RUN;
			case GameStrategy.SHOOT_GAME:
				return TOP_KILL;
		}
		//TODO добавить error
		return null;
	}
}
