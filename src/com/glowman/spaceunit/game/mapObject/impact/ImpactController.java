package com.glowman.spaceunit.game.mapObject.impact;

import android.util.Log;
import com.glowman.spaceunit.game.animation.IBlowMaker;
import com.glowman.spaceunit.game.mapObject.SpaceObject;

import java.util.ArrayList;

/**
 *
 */
public class ImpactController implements IImpactMaker {
    private ArrayList<ISpaceImpact> _impacts;

    private final IBlowMaker _blowMaker;

    public ImpactController(IBlowMaker blowMaker) {
        _impacts = new ArrayList<ISpaceImpact>();
        _blowMaker = blowMaker;
    }

    public void execute(SpaceObject object) {
        for (ISpaceImpact impact : _impacts) {
            impact.execute(object);
        }
    }

    public void tick(float delta) {
        ArrayList<ISpaceImpact> completedImpacts = new ArrayList<ISpaceImpact>();
        for (ISpaceImpact impact : _impacts) {
            if (impact.isComplete()) {
                completedImpacts.add(impact);
            } else {
                impact.tick(delta);
            }
        }

        for (ISpaceImpact impact : completedImpacts) {
            impact.stop();
            _impacts.remove(impact);
            Log.d("pig", "ivan");
        }
        completedImpacts.clear();
    }

    public void createBlow(float x, float y) {
        ISpaceImpact blowImpact = new BlowImpact(x, y, _blowMaker);
        blowImpact.start();
        _impacts.add(blowImpact);
    }

    public void createGravity(float x, float y) {
        ISpaceImpact gravityImpact = new GravityImpact(x, y, _blowMaker);
        gravityImpact.start();
        _impacts.add(gravityImpact);
    }


    public void createAbilityBlow(float x, float y) {
        ISpaceImpact abilityImpact = new AbilityBlowImpact(x, y, _blowMaker);
        abilityImpact.start();
        _impacts.add(abilityImpact);
    }

    public ArrayList<ISpaceImpact> getImpacts() {
        return _impacts;
    }


    public GravityImpact currentImpact() {
        GravityImpact currentImpact = null;
        for (ISpaceImpact impact : _impacts) {
            if (impact instanceof GravityImpact)
                currentImpact = (GravityImpact) impact;
        }
        return currentImpact;
    }
}

