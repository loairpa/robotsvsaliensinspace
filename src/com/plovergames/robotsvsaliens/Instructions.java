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
