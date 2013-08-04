package com.glowman.spaceunit.achievements;

public class AchievementItem {
	public String id;
	public long score;
	public Boolean unlock = false;
	
	public AchievementItem(String id, long score) {
		this.id = id;
		this.score = score;
	}
}
