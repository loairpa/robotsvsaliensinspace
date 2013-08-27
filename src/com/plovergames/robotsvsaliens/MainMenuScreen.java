package com.plovergames.robotsvsaliens;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

import com.plovergames.framework.Game;
import com.plovergames.framework.Input.TouchEvent;
import com.plovergames.framework.gl.Camera2D;
import com.plovergames.framework.gl.SpriteBatcher;
import com.plovergames.framework.impl.GLScreen;
import com.plovergames.framework.math.OverlapTester;
import com.plovergames.framework.math.Rectangle;
import com.plovergames.framework.math.Vector2;


public class MainMenuScreen extends GLScreen {

	Camera2D guiCam;
	SpriteBatcher batcher;
	Rectangle soundBounds;
	Rectangle gameBounds;
	Rectangle tutorialBounds;
	Rectangle highscoresBounds;
	Rectangle helpBounds;
	Vector2 touchPoint; 
	
	
	
	public MainMenuScreen(Game game) {
		super(game);
		guiCam = new Camera2D(glGraphics, 320, 480);
		batcher = new SpriteBatcher(glGraphics, 100);
		soundBounds = new Rectangle(0,0,64,64);
		gameBounds = new Rectangle(100, 210, 200, 64);
		tutorialBounds = new Rectangle(100, 290, 200, 64);
		highscoresBounds = new Rectangle(160-150, 200-18, 300, 36);
		helpBounds = new Rectangle(160-150, 200-18-36, 300, 36);
		touchPoint = new Vector2();
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);                        
            if(event.type == TouchEvent.TOUCH_UP) {
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);
                
                if(OverlapTester.pointInRectangle(gameBounds, touchPoint)) {
                    game.setScreen(new GameScreen(game));
                    return;
                }
                if(OverlapTester.pointInRectangle(tutorialBounds, touchPoint)){
                	game.setScreen(new TutorialScreen(game));
                	return;
                }

   
            }
        }

	}

	@Override
	public void present(float deltaTime) {
		 	GL10 gl = glGraphics.getGL();        
	        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	        guiCam.setViewportAndMatrices();
	        
	        gl.glEnable(GL10.GL_TEXTURE_2D);
	        
	        batcher.beginBatch(Assets.background);
	        batcher.drawSprite(160, 240, 320, 480, Assets.backgroundRegion);

	        batcher.endBatch();
	        gl.glDisable(GL10.GL_BLEND);
	        
	        gl.glEnable(GL10.GL_BLEND);
	        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);               
	        
	        batcher.beginBatch(Assets.items);    
//	        batcher.drawSprite(160, 200, 98, 64, Assets.mainMenu);
	        Assets.font.drawText(batcher, "TURORIAL", 100,300);
	        Assets.font.drawText(batcher, "GAME", 120, 250);
//	        Assets.font.animateText(batcher, "Tutorial", 120, 250, 100000);
	        batcher.endBatch();
	        
	        
	        gl.glDisable(GL10.GL_BLEND);
	}

	@Override
	public void pause() {
		Settings.save(game.getFileIO());

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
