//    This file is part of RobotsVsAliensInSpace. 
//
//	  Author: Lovisa Irpa Helgadottir
//
//    RobotsCsAliensInSpace is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    RobotsCsAliensInSpace is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with RobotsCsAliensInSpace.  If not, see <http://www.gnu.org/licenses/>.
package com.plovergames.robotsvsaliens;

import java.io.IOException;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;

import com.plovergames.framework.Game;
import com.plovergames.framework.Input.TouchEvent;
import com.plovergames.framework.gl.Camera2D;
import com.plovergames.framework.gl.SpriteBatcher;
import com.plovergames.framework.impl.GLScreen;
import com.plovergames.framework.math.OverlapTester;
import com.plovergames.framework.math.Rectangle;
import com.plovergames.framework.math.Vector2;

public class TutorialScreen extends GLScreen {

	static final int MAX_LEVEL =10;
	static final int GAME_RUNNING =1;
	static final int GAME_PAUSED =2;
	static final int GAME_LEVEL_END=3;


	int state;
	Camera2D guiCam;
	Vector2 touchPoint; 
	SpriteBatcher batcher;
	World world;
	//WorldListener listener;
	WorldRenderer renderer;
	LevelLoader tutorial;
	Rectangle pauseBounds;
	Rectangle resumeBounds;

	int tutorialNumber;
	public TutorialScreen(Game game) {
		super(game);
		Log.d("Tutorial Screen","0");
		state = GAME_PAUSED;
		guiCam = new Camera2D(glGraphics, 320,480);
		touchPoint = new Vector2();
		batcher = new SpriteBatcher(glGraphics,1000);
		/*		listener = new WorldListener(){
//			public void jump(){
//				Assets.playSound(Assets.jumpSound);
//			}
//			public void highJump(){
//				Assets.playSound(Assets.highJumpSound);
//			}
//			public void hit(){
//				Assets.playSound(Assets.hitSound);
//			}
//			public void coin(){
//				Assets.playSound(Assets.coinSound);
//			}
//		};_*/
		tutorialNumber=1;
		String filename = "tutorial"+tutorialNumber+".xml";

		try{
			tutorial =new LevelLoader(game, filename);
			world = new World(tutorial);
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		world.tutorial= true;
		renderer= new WorldRenderer(glGraphics, batcher, world);
		pauseBounds = new Rectangle(320-32,0,64,64);
		resumeBounds = new Rectangle(320-32,0,64,64);

	}

	@Override
	public void update(float deltaTime) {
		if(deltaTime > 0.1f)
			deltaTime = 0.1f;
		try{  
			switch(state) {
			case GAME_RUNNING:
				updateRunning(deltaTime);
				break;
			case GAME_PAUSED:
				updatePaused(deltaTime);
				break;
			case GAME_LEVEL_END:
				updateLevelEnd();
				break;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}


	}

	@Override
	public void present(float deltaTime) {
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glEnable(GL10.GL_TEXTURE_2D);

		renderer.render();

		guiCam.setViewportAndMatrices();
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		batcher.beginBatch(Assets.items);
		renderControlPanel();
		switch(state) {
		case GAME_RUNNING:
			presentRunning();
			break;
		case GAME_PAUSED:
			presentPaused();
			break;
		case GAME_LEVEL_END:
			presentPaused();
			break;
		}
		batcher.endBatch();
		gl.glDisable(GL10.GL_BLEND);
	}

	@Override
	public void pause() {
		if(state == GAME_RUNNING)
			state= GAME_PAUSED;

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	private void updateRunning(float deltaTime) throws XmlPullParserException, IOException {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		int len = touchEvents.size();
		for(int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if(event.type != TouchEvent.TOUCH_UP)
				continue;

			touchPoint.set(event.x, event.y);
			guiCam.touchToWorld(touchPoint);

			if(OverlapTester.pointInRectangle(pauseBounds, touchPoint)) {
				state = GAME_PAUSED;
				world.controlPanel.active=0;
				world.controlPanel.currentPanel=0;
				//				world.controlPanel.end= false;
				world.generatelevel();
				world.tutorial = true;
				return;
			}
		}
		
		world.update(deltaTime);

		if(world.controlPanel.end==true){
			state=GAME_PAUSED;
			world.controlPanel.active=0;
			world.controlPanel.currentPanel=0;
			world.controlPanel.paused=true;
			world.controlPanel.end=false;
			world.generatelevel();
			world.tutorial= true;
			return;
		}

		if(world.state == World.WORLD_STATE_NEXT_LEVEL){ 
			state = GAME_LEVEL_END;
			return;
		}

	}

	private void updatePaused(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		int len = touchEvents.size();
		if(world.instructions.size()>0){
			if(len >0 ) world.instructions.get(0).animate_text = world.instructions.get(0).ANIMATE_TEXT_OFF;
			world.instructions.get(0).update(deltaTime);
		}
		for(int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if(event.type != TouchEvent.TOUCH_UP)
				continue;

			touchPoint.set(event.x, event.y);
			guiCam.touchToWorld(touchPoint);
			if(world.instructions.size()>0) world.instructions.get(0).update(deltaTime);

			if(OverlapTester.pointInRectangle(resumeBounds, touchPoint)) {
				//                Assets.playSound(Assets.clickSound);
				state = GAME_RUNNING;
				world.controlPanel.active = 1;
				world.controlPanel.currentPanel=0;
				world.robot.setState(world.controlPanel.commands[0][0]);
				return;
			}else{
				world.controlPanel.menu(touchPoint);

				return;
			}
		}
	}

	private void updateLevelEnd() throws XmlPullParserException, IOException {

		tutorialNumber +=1;
		if(tutorialNumber >MAX_LEVEL){
			game.setScreen(new MainMenuScreen(game));
			return;
		}
		String filename = "tutorial"+tutorialNumber+".xml";

		try{
			tutorial =new LevelLoader(game, filename);
			world = new World(tutorial);
			world.tutorial =true;}
		catch (Exception e) {
			e.printStackTrace();
		}
		renderer = new WorldRenderer(glGraphics, batcher, world);
		state = GAME_PAUSED;

	}

	private void presentRunning() {
		batcher.drawSprite(320-16, 40, 32, 32,  Assets.pause);
	}

	private void presentPaused() {        
		batcher.drawSprite(320-16, 40, 32, 32, Assets.play);
	}

	private void renderControlPanel(){
		batcher.drawSprite(160, 32, 320, 64, Assets.controlpanel);
		switch(world.controlPanel.currentPanel){
		case 1: 
			batcher.drawSprite(16,32,32,32,Assets.one);
			break;
		default: 
			batcher.drawSprite(16,32,32,32,Assets.zero);
			break;
		}
		
		if(world.controlPanel.active>0) batcher.drawSprite(32*(world.controlPanel.active)+16, 32, 32, 64, Assets.active);
		
		for(int i =0; i<8; i++){
			switch(world.controlPanel.commands[world.controlPanel.currentPanel][i]){
			case 1:
				batcher.drawSprite(32*(i+1)+16, 32, 32, 40,Assets.move);
				break;
			case 2:
				batcher.drawSprite(32*(i+1)+16, 32, 32, 40,Assets.turnleft);
				break;
			case 3:
				batcher.drawSprite(32*(i+1)+16, 32, 32, 40, Assets.turnright);
				break;
			case 4:
				batcher.drawSprite(32*(i+1)+16, 32, 32, 40, Assets.one);
				break;
			default: 
				batcher.drawSprite(32*(i+1)+16, 32, 32, 40, Assets.wait);
				break;



			}
		}
	}

}
