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
			if(active==9 && currentPanel==0) 
				end = true;

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
