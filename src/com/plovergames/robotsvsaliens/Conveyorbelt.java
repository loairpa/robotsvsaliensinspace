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
