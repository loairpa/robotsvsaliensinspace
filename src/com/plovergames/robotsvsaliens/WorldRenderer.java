package com.plovergames.robotsvsaliens;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

import com.plovergames.framework.gl.Animation;
import com.plovergames.framework.gl.Camera2D;
import com.plovergames.framework.gl.SpriteBatcher;
import com.plovergames.framework.gl.TextureRegion;
import com.plovergames.framework.impl.GLGraphics;

public class WorldRenderer {

	static final float FRUSTUM_WIDTH = 15;
	static final float FRUSTUM_HEIGHT = 10;
	GLGraphics glGraphics;
	World world;
	Camera2D cam;
	SpriteBatcher batcher;

	public WorldRenderer(GLGraphics glGraphics, SpriteBatcher batcher, World world){
		this.glGraphics= glGraphics;
		this.world=world;
		this.cam= new Camera2D(glGraphics, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		this.batcher=batcher;
	}

	public void render(){
		//if(world.robot.position.y>cam.position.y)
		//cam.position.y=world.robot.position.y;
		cam.setViewportAndMatrices();
		renderBackground();
		renderObjects();
	}

	public void renderBackground() {
		batcher.beginBatch(Assets.background);
		batcher.drawSprite(cam.position.x, cam.position.y,
				FRUSTUM_WIDTH, FRUSTUM_HEIGHT, 
				Assets.backgroundRegion);
		batcher.endBatch();
	}

	public void renderObjects() {
		GL10 gl = glGraphics.getGL();
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		batcher.beginBatch(Assets.items);
		renderInstructions();
		renderControlPanel();
		renderShip();
		renderItems();
		renderBelt();
		renderRobot();
		renderAlien();
		renderLaser();
		batcher.endBatch();
		gl.glDisable(GL10.GL_BLEND);
	}

	private void renderInstructions(){	
		int len = world.instructions.size();
		if(len >0){
			Instructions instructions = world.instructions.get(0);
			if(instructions.animate_text == instructions.ANIMATE_TEXT_ON)
				Assets.font.animateText(batcher, instructions.text, instructions.position.x, instructions.position.y,instructions.TEXTBOX_WIDTH,instructions.TEXTBOX_HEIGHT, instructions.currentLetter);
			else 
				Assets.font.drawText(batcher, instructions.text,instructions.position.x, instructions.position.y,instructions.TEXTBOX_WIDTH,instructions.TEXTBOX_HEIGHT);
		}
	}
	
	private int direction =0;
	private float shrink =1;
	private void renderRobot() {
		TextureRegion keyFrame;
		float side = 1;   

		switch(world.robot.direction) {
		case Robot.ROBOT_DOWN:
			keyFrame = Assets.robotDownAnim.getKeyFrames(world.robot.walkTime, Animation.ANIMATION_LOOPING);
			direction =0; 
			shrink =1;
			break;
		case Robot.ROBOT_LEFT:
			keyFrame = Assets.robotSideAnim.getKeyFrames(world.robot.walkTime, Animation.ANIMATION_LOOPING);
			direction =0; 
			shrink =1;
			break;
		case Robot.ROBOT_RIGHT:
			keyFrame = Assets.robotSideAnim.getKeyFrames(world.robot.walkTime, Animation.ANIMATION_LOOPING);
			direction =0; 
			shrink =1;
			side=-1;
			break;
		case Robot.ROBOT_UP:
			keyFrame = Assets.robotUpAnim.getKeyFrames(world.robot.walkTime, Animation.ANIMATION_LOOPING);
			direction =0; 
			shrink =1;
			break;
		case Robot.ROBOT_STATE_DEAD:
			keyFrame = Assets.robotStop;
			direction +=10;
			if(shrink>0)shrink -=0.01f;
			break;
		default:
			keyFrame = Assets.robotStop;                       
		}


		batcher.drawSprite(world.robot.position.x, world.robot.position.y, shrink*side*Robot.ROBOT_WIDTH, shrink*Robot.ROBOT_HEIGHT,direction ,keyFrame);        
	}

	private void renderAlien(){
		TextureRegion keyframe;
		int len = world.aliens.size();
		for(int i = 0; i<len; i++){
			Alien alien = world.aliens.get(i);
			keyframe = Assets.alienAnim.getKeyFrames(alien.walkTime, Animation.ANIMATION_LOOPING);
			batcher.drawSprite(alien.position.x, alien.position.y,Alien.ALIEN_WIDTH,Alien.ALIEN_HEIGHT, keyframe);
		}
	}
	private void renderShip() {
		int len = world.ship.size();
		float lastX=0;
		for (int i = 0; i<len; i++){
			Ship shippart = world.ship.get(i);
			batcher.drawSprite(shippart.position.x, shippart.position.y, Ship.SHIPPART_WIDTH, Ship.SHIPPART_HEIGHT, Assets.shippart);
			if(shippart.position.x>lastX) lastX=shippart.position.x;   				
		}
		for(int i = 0; i<len; i++){
			Ship shippart = world.ship.get(i); 
			if(shippart.position.x == lastX)
				batcher.drawSprite(lastX+0.7f, shippart.position.y,-1,1,Assets.fire);
		}

	}

	private void renderItems() {
		batcher.drawSprite(world.button.position.x,world.button.position.y,SelfDestructButton.BUTTON_WIDTH,SelfDestructButton.BUTTON_HEIGHT,Assets.selfdestructbutton);
		int len = world.airlocks.size();
		for(int i = 0; i<len;i++){
			batcher.drawSprite(world.airlocks.get(i).position.x, world.airlocks.get(i).position.y, Airlock.AIRLOCK_WIDTH, Airlock.AIRLOCK_HEIGHT, Assets.airlock);
		}


	}

	private void renderBelt(){
		TextureRegion keyframe; 
		int len = world.conveyorbelts.size();
		for(int i=0; i<len; i++){
			Conveyorbelt belt = world.conveyorbelts.get(i);
			keyframe = Assets.conveyorbelt.getKeyFrames(belt.stateTime, Animation.ANIMATION_LOOPING);
			batcher.drawSprite(belt.position.x,belt.position.y,1,1,belt.direction,keyframe);
		}
	}
	private void renderLaser(){
		int len = world.lasers.size();
		for(int i=0; i<len; i++){
			Laser laser = world.lasers.get(i);

			batcher.drawSprite(laser.position.x, laser.position.y, 1.0f, 1.0f,laser.direction,Assets.laser);
			int lenBeam = laser.beam.size();
			for(int j =0; j<lenBeam;j++)
				batcher.drawSprite(laser.beam.get(j).position.x, laser.beam.get(j).position.y, 1, 1,laser.direction, Assets.laserbeam);
			
		}
	}
	private void renderControlPanel() {
		batcher.drawSprite(7.5f, 0.8f, 15, 2f, Assets.controlpanel);
		if(world.controlPanel.active>0) batcher.drawSprite(1.5f*(world.controlPanel.active)+0.8f, 1f, 1.5f, 2f, Assets.active);

		for(int i =0; i<8; i++){
			switch(world.controlPanel.commands[i]){
			case 1:
				batcher.drawSprite(1.5f*(i+1)+0.8f, 1f, 1.f, 1.5f,Assets.move);
				break;
			case 2:
				batcher.drawSprite(1.5f*(i+1)+0.8f, 1f, 1.f, 1.5f,Assets.turnleft);
				break;
			case 3:
				batcher.drawSprite(1.5f*(i+1)+0.8f, 1f, 1.f, 1.5f, Assets.turnright);
				break;
			default: 
				batcher.drawSprite(1.5f*(i+1)+0.8f, 1f, 1.f, 1.5f, Assets.wait);
				break;



			}
		}
	}

}
