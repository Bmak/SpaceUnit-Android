package com.glowman.spaceunit.achievements;

import com.glowman.spaceunit.data.GooglePlayData;
import com.glowman.spaceunit.game.strategy.GameStrategy;

public class AchievementsConrol {
	private static final String achiev_kills_15 = "CgkI8evoqtYQEAIQAw";
	private static final String achiev_kills_100 = "CgkI8evoqtYQEAIQBQ";
	private static final String achiev_kills_500 = "CgkI8evoqtYQEAIQBg";
	
	private static final String achiev_hold_20s = "CgkI8evoqtYQEAIQBA";
	private static final String achiev_hold_3m = "CgkI8evoqtYQEAIQBw";
	private static final String achiev_hold_10m = "CgkI8evoqtYQEAIQCA";
	
	private static AchievementItem[] _runAchievements;
	private static AchievementItem[] _shootAchievements;
	
	public static void init() {
		_runAchievements = new AchievementItem[3];
		_runAchievements[0] = new AchievementItem(achiev_hold_20s, 20000);
		_runAchievements[1] = new AchievementItem(achiev_hold_3m, 180000);
		_runAchievements[2] = new AchievementItem(achiev_hold_10m, 600000);
		
		_shootAchievements = new AchievementItem[3];
		_shootAchievements[0] = new AchievementItem(achiev_kills_15, 15);
		_shootAchievements[1] = new AchievementItem(achiev_kills_100, 100);
		_shootAchievements[2] = new AchievementItem(achiev_kills_500, 500);
	}
	
	public static void checkUnlockAchievement(int gameType, long score) {
		if (GooglePlayData.gameHelper.isSignedIn()) {
			switch (gameType) {
				case GameStrategy.RUN_GAME:
					checkUnlockRunAchievement(score);
					break;
				case GameStrategy.SHOOT_GAME:
					checkUnlockShootAchievement(score);
					break;
			}
    	}
	}
	
	private static void checkUnlockRunAchievement(long score) {
		for (AchievementItem achieve : _runAchievements) {
			if (achieve.score <= score && !achieve.unlock) {
				//achieve.unlock = true;
				GooglePlayData.gamesClient.unlockAchievement(achieve.id);
			}
		}
	}
	
	private static void checkUnlockShootAchievement(long score) {
		for (AchievementItem achieve : _shootAchievements) {
			if (achieve.score <= score && !achieve.unlock) {
				//achieve.unlock = true; For other users this achive will be unlock in currently game session
				GooglePlayData.gamesClient.unlockAchievement(achieve.id);
			}
		}
	}
	
}
