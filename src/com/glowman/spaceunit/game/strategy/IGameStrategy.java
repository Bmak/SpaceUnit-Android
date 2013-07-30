package com.glowman.spaceunit.game.strategy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.glowman.spaceunit.core.TouchEvent;

/**
 *
 */
public interface IGameStrategy {
	void startGame();
	void stopGame();
	void pauseGame();
	void resumeGame();

	void tick(float delta);
	GameStatus getGameStatus();
	Sprite[] getDrawableObjects();

	void touchUp(TouchEvent touch);
	void touchDown(TouchEvent touch);
	void touchMove(TouchEvent touch);
}
