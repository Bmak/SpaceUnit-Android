package com.glowman.spaceunit.game.score;

/**
 *
 */
public class Score {
	public static final String SECONDS = "seconds";
	public static final String POINTS = "points";

	public String type;
	public float score;

	public Score(String type, float score) {
		this.type = type;
		this.score = score;
	}

	public float getPrintableScore() {
		if (type == SECONDS) {
			return ((int)(score / 10))/100f;
		}
		return score;
	}
}
