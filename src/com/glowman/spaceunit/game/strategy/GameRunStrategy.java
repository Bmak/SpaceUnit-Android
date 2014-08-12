package com.glowman.spaceunit.game.strategy;

import android.util.Log;
import com.glowman.spaceunit.Assets;
import com.glowman.spaceunit.core.TouchEvent;
import com.glowman.spaceunit.game.ability.Ability;
import com.glowman.spaceunit.game.ability.AbilityENUM;
import com.glowman.spaceunit.game.balance.RespawnFrequencyCollector;
import com.glowman.spaceunit.game.balance.RotationSpeedCollector;
import com.glowman.spaceunit.game.mapObject.enemy.EnemyTypeENUM;
import com.glowman.spaceunit.game.score.Score;
import com.glowman.spaceunit.game.balance.EnemySetCollector;
import com.glowman.spaceunit.game.balance.SpeedCollector;
import com.glowman.spaceunit.game.mapObject.enemy.BehaviourOptionsData;
import com.glowman.spaceunit.game.mapObject.enemy.Enemy;
import com.glowman.spaceunit.game.mapObject.hero.Ship;
import com.glowman.spaceunit.game.mapObject.enemy.EnemyFactory;

import java.util.ArrayList;

/**
 *
 */
public class GameRunStrategy extends GameStrategy {

	private Score _score;
	private Ability _ability;

	public GameRunStrategy(Ship ship)
	{
		super(ship, GameStrategy.RUN_GAME);
		_ability = Ability.create(AbilityENUM.BLOW, ship, super._impactController);
		_score = new Score(Score.getScoreTypeByGameType(GameStrategy.RUN_GAME), 0);

        BehaviourOptionsData bhOptions = new BehaviourOptionsData(null, _blowController, ship, _impactController);
		EnemyFactory.init(GameStrategy.RUN_GAME, _heroShip, bhOptions);
		_availableEnemyTypes = EnemySetCollector.getEnemySet(GameStrategy.RUN_GAME);
	}

	@Override
	public Score getScore() {
		return _score;
	}




	@Override
	public void tick(float delta)
	{
		if (super.getGameStatus() == GameStatus.IN_PROCESS) _score.score+= delta * 1000;
		super.tick(delta);
		_ability.tick(delta);
		super._heroShip.tick(delta);

		if (_enemies != null)
		{
            this.checkHeroHit();
            this.checkEnemyHits();

		}
	}

	@Override
	public Ability getAbility() {
		return _ability;
	}
	@Override
	public void useAbility() {
		if (!_ability.isReady()) {
			throw new Error("ability not ready for use");
		}
		_ability.activate();
	}




	@Override
	public void touchMove(TouchEvent touch) {
	}

    private final int _gameType = GameStrategy.RUN_GAME;


    @Override
    protected void createEnemies(){
		if (_availableEnemyTypes == null || _availableEnemyTypes.length == 0) {
			return;
		}
		String enemyType;
		float frequency;

		for (int i = 0; i < _availableEnemyTypes.length; ++i) {
			enemyType = _availableEnemyTypes[i];
			frequency = RespawnFrequencyCollector.getFrequency(enemyType, _gameType, _timeState);
			if (Math.random() < frequency && this.getHolesNumber() < 2) {
				Enemy enemy = EnemyFactory.createEnemy(enemyType);
				_enemies.add(enemy);
				this.setEnemyParams(enemy);

			}
		}
	}
    private int getHolesNumber(){
        int number = 0;
        for (Enemy enemy : _enemies){
            if (enemy.getEnemyType()== EnemyTypeENUM.BLACK_HOLE){
                number++;
            }
        }
        return number;
    }

	@Override
	protected void setEnemyParams(Enemy enemy) {
		enemy.setGeneralSpeed(SpeedCollector.getEnemySpeed(enemy.getEnemyType(), GameStrategy.RUN_GAME));
        enemy.setRotationSpeed(RotationSpeedCollector.getEnemyRotation());
        enemy.setRandomBorderPosition();
        enemy.setTarget(_heroShip);
        if (enemy.getEnemyType() == EnemyTypeENUM.BLACK_HOLE) enemy.moveTo((float) Math.random() * Assets.VIRTUAL_WIDTH,
            (float) Math.random() * Assets.VIRTUAL_HEIGHT); //черная дыра
	}

	private void checkHeroHit() {
		Enemy enemyToExplode = null;
		for (Enemy enemy : _enemies) {
            if (super.checkObjectsHit(_heroShip, enemy)) {// бессм черной дыры
                enemyToExplode = enemy;
                super.gameOver();
                break;
            }
        }

            if (enemyToExplode != null )
            {
                if (enemyToExplode.getEnemyType() != EnemyTypeENUM.BLACK_HOLE)//тут
                {
                    super.explodeEnemy(enemyToExplode);
                }

                super.explodeHero();
            }
        }

    private void checkEnemyHits()
    {
        ArrayList<Enemy> enemiesForExplosion = new ArrayList<Enemy>();
        for (int i = 0; i < (_enemies.size() - 1); ++i) {
            for (int j = i + 1; j < _enemies.size(); ++j) {

                if (super.checkObjectsHit(_enemies.get(i), _enemies.get(j))) {
                    if(!this.isEnemyBlackHole(_enemies.get(i)) || this.isEnemyBlackHole(_enemies.get(j)))
                        enemiesForExplosion.add(_enemies.get(i));
                    if(!this.isEnemyBlackHole(_enemies.get(j)) || this.isEnemyBlackHole(_enemies.get(i)))
                        enemiesForExplosion.add(_enemies.get(j));
                }
            }
        }

        for (Enemy enemy : enemiesForExplosion)
        {
            super.explodeEnemy(enemy);
        }
    }

    private boolean isEnemyBlackHole(Enemy enemy)
    {
        return enemy.getEnemyType() == EnemyTypeENUM.BLACK_HOLE;
    }

}