package com.glowman.spaceunit.game.strategy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.glowman.spaceunit.core.TouchEvent;
import com.glowman.spaceunit.game.score.Score;

import java.util.ArrayList;

/**
 *
 */
public interface IGameStrategy {
	void startGame();
	void stopGame();
	void pauseGame();
	void resumeGame();

	Score getScore();

	void tick(float delta);
	GameStatus getGameStatus();
	ArrayList<Sprite> getDrawableObjects();

	void touchUp(TouchEvent touch);
	void touchDown(TouchEvent touch);
	void touchMove(TouchEvent touch);
}
