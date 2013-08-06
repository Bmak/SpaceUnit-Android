package com.glowman.spaceunit.game.score;

import com.glowman.spaceunit.game.strategy.GameStrategy;

/**
 *
 */
public class Score {
	public static final String SECONDS = "seconds";
	public static final String KILLS = "kills";

	public String type;
	public float score;

	public static String getScoreTypeByGameType(int gameType) {
		String result;
		if (gameType == GameStrategy.SHOOT_GAME) result = KILLS;
		else result = SECONDS;
		return result;
	}

	public Score(String type, float score) {
		this.type = type;
		this.score = score;
	}

	public String getPrintableScore() {
		if (type == SECONDS) {
			float cutedScore = ((int)(score / 10))/100f;
			int s = (int) (cutedScore);
			int m = (int) (s / 60f);
			int ms = (int) ((cutedScore - s) * 10);
			s = (int) (s % 60);
			return stringifyTime(m) + ":" + stringifyTime(s) + ":" + stringifyTime(ms);
		}
		return String.valueOf((int)score);
	}

	private String stringifyTime(int time) {
		return time < 10 ? "0" + time : String.valueOf(time);
	}

}
