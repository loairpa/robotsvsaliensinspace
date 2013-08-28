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

import com.plovergames.framework.GameObject;

public class Ship extends GameObject {
	
	public static float SHIPPART_WIDTH =1.0f;
	public static float SHIPPART_HEIGHT =1.0f;
	


	public Ship(float x, float y) {
		super(x, y,  SHIPPART_WIDTH, SHIPPART_HEIGHT);
		// TODO Auto-generated constructor stub
	}

}
