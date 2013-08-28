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
import com.plovergames.framework.math.Rectangle;

public class Conveyorbelt extends DynamicGameObject {
    public static final float BELT_WIDTH =1.0f;
    public static final float BELT_HEIGHT =0.5f;
    
    public static final int BELT_UP =0;
    public static final int BELT_LEFT =90;
    public static final int BELT_DOWN =180;
    public static final int BELT_RIGHT = 270;
    
    int direction;
    float stateTime =0.0f;
	public Conveyorbelt(float x, float y, int direction) {
		super(x, y, BELT_WIDTH, BELT_HEIGHT);
		this.direction = direction; 

		if(direction==BELT_UP || direction == BELT_DOWN){
			this.bounds.lowerLeft.x = x-BELT_HEIGHT/2;
			this.bounds.lowerLeft.y=y-BELT_WIDTH/2;
			this.bounds.width=BELT_HEIGHT;
			this.bounds.height=BELT_WIDTH;
						
		}else{
			this.bounds.lowerLeft.y = y-BELT_HEIGHT/2;
			this.bounds.lowerLeft.x=x-BELT_WIDTH/2;
			this.bounds.height=BELT_HEIGHT;
			this.bounds.width=BELT_WIDTH;
		}
		// TODO Auto-generated constructor stub
	}

	public void update(float deltaTime){
		stateTime +=deltaTime;
	}
}
