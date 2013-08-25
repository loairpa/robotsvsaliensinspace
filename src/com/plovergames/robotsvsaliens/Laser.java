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
