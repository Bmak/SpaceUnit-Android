package com.glowman.androidgames.bolvanka;

import java.util.List;

import android.graphics.Color;

import com.glowman.android.framework.Game;
import com.glowman.android.framework.Graphics;
import com.glowman.android.framework.Input;
import com.glowman.android.framework.Screen;
import com.glowman.android.framework.math.OverlapTester;
import com.glowman.android.framework.math.Rectangle;
import com.glowman.android.framework.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: MAX
 * Date: 10.07.13
 * Time: 1:38
 * To change this template use File | Settings | File Templates.
 */
public class TestScreenOne extends Screen {
	
	Rectangle backBtn;
    Vector2 touchPoint;
    Graphics g;
    int btnColor;
	
    public TestScreenOne(Game game) {
        super(game);

        g = game.getGraphics();
        
        backBtn = new Rectangle(10, 10, 100, 50);
        btnColor = Color.GREEN;
        touchPoint = new Vector2();
    }

    @Override
    public void update(float deltaTime) {
    	 List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
         game.getInput().getKeyEvents();
         int len = touchEvents.size();

         for(int i = 0; i < len; i++) {
             Input.TouchEvent event = touchEvents.get(i);
             touchPoint.set(event.x,event.y);
             
             if (OverlapTester.pointInRectangle(backBtn, touchPoint)) {
            	 if(event.type == Input.TouchEvent.TOUCH_DOWN) {
                  	btnColor = Color.YELLOW;
            	 } else if(event.type == Input.TouchEvent.TOUCH_UP) {
            		btnColor = Color.GREEN;
            		g.clear(Color.BLACK);
            		game.setScreen(new MainScreen(game));
            	 } 
             } else {
             	btnColor = Color.GREEN;
             }
         }
    }

    @Override
    public void present(float deltaTime) {
    	g.drawRect(
                (int)(backBtn.lowerLeft.x), (int)(backBtn.lowerLeft.y),
                (int)(backBtn.width), (int)(backBtn.height),  btnColor);
        
        g.drawText("back", (int)(backBtn.lowerLeft.x + backBtn.width/2),
        		(int)(backBtn.lowerLeft.y + backBtn.height/2), 30, Color.BLACK);
        
        g.drawText("O_o", g.getWidth()/2, g.getHeight()/2, 60, Color.WHITE);
    }

    @Override
    public void pause() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void resume() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dispose() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
