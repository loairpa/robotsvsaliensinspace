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

public class Rotator extends DynamicGameObject {
    public static final float ROTATOR_WIDTH =1.0f;
    public static final float ROTATOR_HEIGHT =1.0f;
    
    public static final int COUNTER_CLOCKWISE = -1;
    public static final int CLOCKWISE = 1; 
    

    
    int direction;
    float spin =0;
    float stateTime =0.0f;
	public Rotator(float x, float y, int direction) {
		super(x, y, ROTATOR_WIDTH, ROTATOR_HEIGHT);
		this.direction = direction;
	}

	public void update(float deltaTime){
		spin -=direction*0.5f;
		
	}
}
