package com.plovergames.robotsvsaliens;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.res.XmlResourceParser;
import android.util.Log;

import com.plovergames.framework.math.OverlapTester;
import com.plovergames.framework.math.Vector2;
import com.plovergames.framework.FileIO;

public class World {
	public interface WorldListener{
		public void jump();
		public void highJump();
		public void hit();
		public void coin();
	}
   
	public static final float WORLD_WIDTH =10;
	public static final float WORLD_HEIGHT =15;
	public static final int WORLD_STATE_RUNNING=0;
	public static final int WORLD_STATE_NEXT_LEVEL=1;
	public static final int WORLD_STATE_GAME_OVER=2;
	
	public ControlPanel controlPanel; 
	public SelfDestructButton button;
	public Robot robot;
	
	public List<Ship> ship;
	public List <Alien> aliens;
	public List <Laser> lasers;
	public List <Airlock> airlocks;
	public List <Conveyorbelt> conveyorbelts;
	public int score; 
	public int state;
	public LevelLoader level;
	
	private float oldDeltaTime=0.0f;
	private Vector2 oldPosition; 
	public World(LevelLoader level) throws XmlPullParserException, IOException{

		this.level = level;
		this.score= 0;
		this.state = WORLD_STATE_RUNNING;
		this.ship = new ArrayList<Ship>();
		this.robot = new Robot();
		this.button = new SelfDestructButton();
		this.aliens =  new ArrayList<Alien>();
		this.lasers = new ArrayList<Laser>();
		this.airlocks = new ArrayList<Airlock>();
		this.conveyorbelts = new ArrayList<Conveyorbelt>();
		this.controlPanel = new ControlPanel();

		generatelevel();
		}
	
	public void generatelevel() throws XmlPullParserException, IOException{
		ship.clear();
		aliens.clear();
		lasers.clear();
		airlocks.clear();
		conveyorbelts.clear();
		robot = new Robot();
		button = new SelfDestructButton();
		level.loadLevel(ship,robot,button,aliens,lasers,airlocks, conveyorbelts);
		oldPosition = new Vector2(robot.position.x,robot.position.y);
		
	}

	public void update(float deltaTime){	
        updateRobot(deltaTime);
        updateAlien(deltaTime);
        updateItems(deltaTime);     
	}
	

	private void updateRobot(float deltaTime){

	if(controlPanel.commands[controlPanel.active-1] == ControlPanel.MOVE && !checkAtEdge() ){
		oldDeltaTime = 0.0f;
		robot.update(deltaTime);
			if(oldPosition.dist(robot.position)>=1.0f){
				updateControlPanel();
				oldPosition.set(robot.position);
			}

		}
	else{
		oldDeltaTime+=deltaTime;
		if(checkAtEdge())
			robot.hitEdge();
	
		if(oldDeltaTime >=1.0f){
			oldDeltaTime=0.0f;
			updateControlPanel();	

		}			
	}			
		if(checkAtButton()){
			Log.d("checkAtButton","True");
			state=WORLD_STATE_NEXT_LEVEL;
		}
		if(checkAlienCollision() || checkLaserCollision()){
			Log.d("checkAlienCollision()","True");
			controlPanel.active = 0;
		}
		if(checkAirlockCollision()){
			Log.d("checkAlienCollision()","True");
			robot.direction = Robot.ROBOT_STATE_DEAD;
		}
		
		if(checkAtBelt(deltaTime)){			
			controlPanel.paused=true;
			Log.d("checkAtBelt","True");	
		}
		else
			controlPanel.paused = false;		
	}
	
	private void updateControlPanel(){
		controlPanel.update();		
		robot.setState(controlPanel.commands[controlPanel.active-1]);
	}
	
	
	private boolean checkAtEdge(){
		int len = ship.size();
		if(controlPanel.paused) 
			return false;
		for(int i =0; i<len; i++){
			Ship shippart = ship.get(i);
			if(OverlapTester.onTopOfRectangles(robot.bounds,shippart.bounds,robot.direction))
				return false;
			
		}
		return  true;
	}
	private boolean checkAtBelt(float deltaTime){
		int len = conveyorbelts.size();
		for(int i =0; i<len; i++){
			Conveyorbelt belt = conveyorbelts.get(i);
			if(OverlapTester.overlapHalfXRectangles(robot.bounds, belt.bounds, robot.direction)){			
				robot.onConveyorbelt(belt.direction,deltaTime);
				moveRobotAfterBelt(i);			
				return true;
			}
						
		}
		return false;
	}
	
	private void moveRobotAfterBelt(int currentbelt){
		Conveyorbelt belt = conveyorbelts.get(currentbelt);
		int dir = Math.abs(belt.direction-robot.direction);
		switch(dir){
		case 0: 
			oldPosition.set(belt.position);
		case 90:
			oldPosition.set(belt.position);
		case 180:
			oldPosition.set(belt.position);
		case 270: 
			oldPosition.set(belt.position);
		}
		
			
	}
	
	private boolean checkAtButton(){
		if(OverlapTester.overlapHalfRectangles(robot.bounds, button.bounds))
			return true;
		return false;
	}
	
	private boolean checkAlienCollision(){
		for(int i=0; i<aliens.size();i++){		
		if(OverlapTester.overlapHalfRectangles(robot.bounds,aliens.get(i).bounds))
			return true;
		}
		return false;
	}
	
	private boolean checkLaserCollision(){
		int len = lasers.size();
		for(int i = 0; i<len; i++){
			Laser laser = lasers.get(i);

		if(OverlapTester.overlapHalfRectangles(robot.bounds,laser.bounds))
			return true;
		int lenbeam = laser.beam.size();
		for(int j =0; j<lenbeam;j++){
			if(OverlapTester.overlapHalfRectangles(robot.bounds,laser.beam.get(j).bounds))
				return true;
		}
		
		}
		return false;
	}
	
	private boolean checkAirlockCollision(){
		for(int i=0; i<airlocks.size();i++){
		if(OverlapTester.overlapHalfRectangles(robot.bounds, airlocks.get(i).bounds))
			return true;
		}return false;
	}
	
	private void updateAlien(float deltaTime){
		for(int i=0; i<aliens.size();i++){
			Alien alien = aliens.get(i);
		if(!checkAlienAtEdge(alien))alien.atEdge = true; 
		alien.update(deltaTime);
		}
		
	}
	private boolean checkAlienAtEdge(Alien alien){
		int len = ship.size();
		for(int i =0; i<len; i++){
			Ship shippart = ship.get(i);
			int dir = alien.direction;
		if(alien.velocity.x >0 || alien.velocity.y<0){
			dir+=180;
		}
		if(OverlapTester.onTopOfRectangles(alien.bounds, shippart.bounds, dir))
			return true;
		}
		return false;
	}
	
	private void updateItems(float deltaTime){
		for(int i = 0; i<conveyorbelts.size();i++){
			conveyorbelts.get(i).update(deltaTime);
		}
	}
}
