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

import java.util.ArrayList;
import java.util.List;

import com.plovergames.framework.GameObject;

public class Laser extends GameObject {
    public static final float LASER_WIDTH =0.5f;
    public static final float LASER_HEIGHT =0.5f;
    

    int direction;
    int touched;
    List<LaserBeam> beam;
	public Laser(float x, float y, int direction, int length) {
		super(x, y, LASER_WIDTH, LASER_HEIGHT);
		this.direction = direction;
		this.touched = 0;
		beam = new ArrayList<LaserBeam>();
		
		int len = length;
		for(int i=0; i<len ; i++){
			switch(direction){
			case 0:
				beam.add(new LaserBeam(x+i+1,y));
				break;
			case 90: 
				beam.add(new LaserBeam(x,y+i+1));
				break;
			case 180:
				beam.add(new LaserBeam(x-i-1,y));
				break;
			case 270:
				beam.add(new LaserBeam(x,y-i-1));
				break;
			}
			
		}

	}
	
	
	static class LaserBeam extends GameObject{
		int touched;
		LaserBeam(float x, float y){
		super(x, y, LASER_WIDTH, LASER_HEIGHT);
		this.touched= 0; 
		}
	}

}
