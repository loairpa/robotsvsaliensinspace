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

import android.util.Log;

import com.plovergames.framework.DynamicGameObject;

public class Robot extends DynamicGameObject {

    public static final float ROBOT_WIDTH =1f;
    public static final float ROBOT_HEIGHT =1f;
    public static final float ROBOT_VELOCITY =1.0f;
    
    
    public static final int ROBOT_UP = 0;
    public static final int ROBOT_LEFT =90;
    public static final int ROBOT_DOWN =180;
    public static final int ROBOT_RIGHT = 270;
    
    public static final int ROBOT_STATE_ACTIVE=0;
    public static final int ROBOT_STATE_STOP = 1;
    public static final int ROBOT_STATE_TURNING = 2; 
    public static final int ROBOT_STATE_ON_BELT=3;
    public static final int ROBOT_STATE_DEAD = 4;
    public static final int ROBOT_HIT_BY_LASER = 5;
    public static final int ROBOT_CAUGHT_IN_AIRLOCK =6;
    public static final int ROBOT_STATE_ON_ROTATOR =7;

    
    int direction; 
    int state;
    boolean alive;
	public float walkTime=0;
	public float stateTime=0;
	public Robot() {
		super(0, 0, ROBOT_WIDTH, ROBOT_HEIGHT);
		this.direction = ROBOT_DOWN;
		this.state = ROBOT_STATE_ACTIVE;
		this.alive = true;
		// TODO Auto-generated constructor stub
	}
	
	public void update(float deltaTime){
		

		
		if(state == ROBOT_STATE_ACTIVE ){
			
		switch(direction){
		case ROBOT_DOWN:
			velocity.set(0, -ROBOT_VELOCITY);
			break;
		case ROBOT_UP:
			velocity.set(0, ROBOT_VELOCITY);
			break;
		case ROBOT_LEFT:
			velocity.set(-ROBOT_VELOCITY,0);
			break;
		case ROBOT_RIGHT:
			velocity.set(ROBOT_VELOCITY,0);
			break;
		default:
		 velocity.set(0,0);
		 break;
		}
		
		walkTime +=deltaTime;
		}else if(state==ROBOT_STATE_TURNING ||  state == ROBOT_HIT_BY_LASER ){
			velocity.set(0,0);
			stateTime+=deltaTime;
			if(stateTime>1.0f){
				state= ROBOT_STATE_ACTIVE;
				stateTime=0.0f;
			}
			
		}
			
		
		position.add(velocity.x*deltaTime, velocity.y*deltaTime);
		bounds.lowerLeft.set(position).sub(bounds.width/2, bounds.height/2);
/*		Log.d("Direction",""+direction);
		Log.d("Position",""+position.x+","+position.y);*/

	}

	public void hitEdge(){
//		state= ROBOT_STATE_STOP;
//		velocity.set(0,0);
//		walkTime=0;
		Log.d("hitEdge","True");

	}
	
	
	public void onConveyorbelt(int beltdir, float deltaTime){	
		state = ROBOT_STATE_ON_BELT;	
		switch(beltdir){
		case Conveyorbelt.BELT_UP:
			velocity.set(0,2f*ROBOT_VELOCITY);
			break;
		case Conveyorbelt.BELT_DOWN:
			velocity.set(0,-2f*ROBOT_VELOCITY);
			break;
		case Conveyorbelt.BELT_LEFT:
			velocity.set(-2f*ROBOT_VELOCITY,0);
			break;
		case Conveyorbelt.BELT_RIGHT:
			velocity.set(2f*ROBOT_VELOCITY,0);
			break;
		default:
		    velocity.set(0,0);
			break;
			
		} stateTime+=deltaTime;
		if(stateTime>0.1f){
			state = ROBOT_STATE_ACTIVE;
			stateTime =0.0f;			
		}
		
	}
	
	public void setState(int command){
		switch(command){	
		case ControlPanel.MOVE:
			state = ROBOT_STATE_ACTIVE;
			break;
		case ControlPanel.TURN_LEFT:
			state = ROBOT_STATE_TURNING;
			direction -=90;
			position.x = Math.round(position.x)+0.1f;
			position.y = Math.round(position.y);
			bounds.lowerLeft.set(position).sub(bounds.width/2, bounds.height/2);
			if(direction <0) direction = 270;
			break;
		case ControlPanel.TURN_RIGHT:
			state = ROBOT_STATE_TURNING;
			direction +=90;
			position.x = Math.round(position.x)+0.1f;
			position.y = Math.round(position.y);
			bounds.lowerLeft.set(position).sub(bounds.width/2, bounds.height/2);
			if(direction >270) direction =0;
			break;
		case ControlPanel.WAIT:
			state = ROBOT_STATE_STOP;
			break;

		}

	}
}
