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

import com.plovergames.framework.GameObject;

public class Instructions extends GameObject {
	public static final float TEXTBOX_WIDTH =0.45f;
	public static final float TEXTBOX_HEIGHT =0.45f;
	public static final int ANIMATE_TEXT_OFF = 0;
	public static final int ANIMATE_TEXT_ON = 1;
	public String text; 

	public float stateTime=0.0f;
	public int currentLetter = 0;
	public int animate_text;

	public Instructions(float x, float y,String string) {
		super(x, y, TEXTBOX_WIDTH, TEXTBOX_HEIGHT);
		this.text = string;
		this.animate_text = ANIMATE_TEXT_ON;
		

		// TODO Auto-generated constructor stub
	}

	public void update(float deltaTime){
		if(animate_text == ANIMATE_TEXT_ON){
			stateTime+=deltaTime;
			if(stateTime>0.10f){
				if(currentLetter < this.text.length())
					currentLetter++;
				else 
					currentLetter=this.text.length();

				stateTime=0.0f;
			}
		}
		else currentLetter = this.text.length();

	}
	


}
