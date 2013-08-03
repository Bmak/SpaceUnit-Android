package com.glowman.spaceunit.game.strategy;

import com.glowman.spaceunit.core.TouchEvent;
import com.glowman.spaceunit.game.score.Score;
import com.glowman.spaceunit.game.balance.EnemySetCollector;
import com.glowman.spaceunit.game.balance.SpeedCollector;
import com.glowman.spaceunit.game.mapObject.enemy.BehaviourOptionsData;
import com.glowman.spaceunit.game.mapObject.enemy.Enemy;
import com.glowman.spaceunit.game.mapObject.Ship;
import com.glowman.spaceunit.game.mapObject.enemy.EnemyFactory;

import java.lang.Math;
import java.util.ArrayList;

/**
 *
 */
public class GameRunStrategy extends GameStrategy {

	private float _score;

	public GameRunStrategy(Ship ship)
	{
		super(ship);
		_score = 0;
		BehaviourOptionsData bhOptions = new BehaviourOptionsData(null, _blowController, ship, _impactController);
		EnemyFactory.init(GameStrategy.RUN_GAME, _heroShip, bhOptions);
		_availableEnemyTypes = EnemySetCollector.getEnemySet(GameStrategy.RUN_GAME);
	}

	@Override
	public Score getScore() {
		//TODO в отображение выводить в таком формате 32:53:12 в таком же виде идет запись в топы
		return new Score(Score.SECONDS, _score*1000f);
	}

	@Override
	public void tick(float delta)
	{
		if (super.getGameStatus() == GameStatus.IN_PROCESS) _score+= delta;
		super.tick(delta);
		super._heroShip.tick(delta);

		if (_enemies != null)
		{
			this.checkEnemyHits();
			this.checkHeroHit();
		}
	}

	@Override
	public void touchDown(TouchEvent touch) {
		_heroShip.moveTo(touch.x, touch.y);
	}

	@Override
	public void touchUp(TouchEvent touch) {

		_heroShip.stopMoving();
	}

	@Override
	public void touchMove(TouchEvent touch) {

		_heroShip.moveTo(touch.x, touch.y);
	}

	@Override
	protected void setEnemyParams(Enemy enemy) {
		enemy.setRandomBorderPosition();
		enemy.setRotationSpeed(5 * ((float)Math.random() * 2 - 1)); //TODO kick it out
		enemy.setGeneralSpeed(SpeedCollector.getEnemySpeed(enemy.getEnemyType(), GameStrategy.RUN_GAME));
		enemy.setTarget(_heroShip);
	}

	private void checkHeroHit() {
		Enemy enemyToExplode = null;
		for (Enemy enemy : _enemies) {
			if (super.checkObjectsHit(_heroShip, enemy)) {
				enemyToExplode = enemy;
				super.gameOver();
				break;
			}
		}

		if (enemyToExplode != null) {
			super.explodeEnemy(enemyToExplode);
			super.explodeHero();
		}
	}

	private void checkEnemyHits()
	{
		ArrayList<Enemy> enemiesForExplosion = new ArrayList<Enemy>();
		for (int i = 0; i < (_enemies.size() - 1); ++i) {
			for (int j = i + 1; j < _enemies.size(); ++j) {

				if (super.checkObjectsHit(_enemies.get(i), _enemies.get(j)))
				{
					enemiesForExplosion.add(_enemies.get(i));
					enemiesForExplosion.add(_enemies.get(j));
				}
			}
		}

		for (Enemy enemy : enemiesForExplosion)
		{
			super.explodeEnemy(enemy);
		}
	}

}
