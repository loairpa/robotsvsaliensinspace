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

import com.plovergames.framework.Input.TouchEvent;
import com.plovergames.framework.math.OverlapTester;
import com.plovergames.framework.math.Rectangle;
import com.plovergames.framework.math.Vector2;

import android.util.Log;


public class ControlPanel  {
	/*
	Control Panel class,  Keep a list of actions and updates current action
	*/

	public static final int WAIT =0;
	public static final int MOVE =1;
	public static final int TURN_RIGHT =2;
	public static final int TURN_LEFT =3;
	public static final int NEXT_PANEL = 4; 
	
	public int[][] commands; 
	public int active;
	public int[] activePanel;
	public int controlPanelNumber=1;
	public int currentPanel = 0;
	public boolean paused=false;
	public boolean end = false;
	public boolean PopUpMenu = false; 
	List <Rectangle> commandSelect;
	Rectangle panelSelect;
	public ControlPanel() {
		this.commands = new int[controlPanelNumber][];
		commands[0] = new int[8];
		this.activePanel = new int[controlPanelNumber];
		this.active=0;
		commandSelect = new ArrayList<Rectangle>();
		for(int i =0;i<8;i++)
			commandSelect.add(new Rectangle(32+i*32,0,32,64));
		
		panelSelect = new Rectangle(0,0,32,64);
		
		

	}
	

	public void update(){
		if(!paused){
			active +=1;

			if(active ==9 && currentPanel>0){
				currentPanel--;
				active= activePanel[currentPanel];
			}
			if(active==9 && currentPanel==0){ 
				end = true;
				paused = true;
			}

		}	

	}


	public void menu(Vector2 touch){
		for(int i = 0; i<8; i++){
			Rectangle selected= commandSelect.get(i);
			if(OverlapTester.pointInRectangle(selected, touch)){
				commands[currentPanel][i]+=1;
				if (commands[currentPanel][i]>2+controlPanelNumber-currentPanel) commands[currentPanel][i]=0;

			return;
			}
			if(OverlapTester.pointInRectangle(panelSelect,touch)){
				currentPanel +=1;
				if(currentPanel== controlPanelNumber) currentPanel = 0;
				return;
			}
		}
	}
	
	public void addPanel(){
		this.commands = new int[controlPanelNumber][];
		this.activePanel = new int[controlPanelNumber];
		for(int i=0; i < controlPanelNumber;i++)
			commands[i]  = new int[8];
		
	}
	

	


}
