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

import com.plovergames.framework.DynamicGameObject;

public class Alien extends DynamicGameObject {
	public static final float ALIEN_WIDTH =1f;
    public static final float ALIEN_HEIGHT =1f;
    public static final float ALIEN_VELOCITY =0.4f;
    
    public static final int ALIEN_STATE_UP_DOWN =0;
    public static final int ALIEN_STATE_LEFT_RIGHT =90;
    
    
    public float walkTime=0;
    boolean atEdge= false;
    int direction;
	public Alien(float x, float y, int direction) {
		super(x, y, ALIEN_WIDTH, ALIEN_HEIGHT);
		this.direction = direction;
		if(direction ==0)
			velocity.set(0, ALIEN_VELOCITY);
		else
			velocity.set(ALIEN_VELOCITY, 0);
		// TODO Auto-generated constructor stub
	}
	
	public void update(float deltaTime){
		position.add(velocity.x*deltaTime, velocity.y*deltaTime);
		bounds.lowerLeft.set(position).sub(bounds.width/2, bounds.height/2);
		
		if(atEdge){
			velocity.set(-1*velocity.x,-1*velocity.y);
			atEdge=false;
		}
		
		walkTime +=deltaTime;
	}

}
