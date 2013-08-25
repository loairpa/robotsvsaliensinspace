package com.plovergames.robotsvsaliens;

import com.plovergames.framework.DynamicGameObject;

public class Alien extends DynamicGameObject {
	public static final float ALIEN_WIDTH =1f;
    public static final float ALIEN_HEIGHT =1f;
    public static final float ALIEN_VELOCITY =0.4f;
    
    public static final int ALIEN_STATE_UP_DOWN =0;
    public static final int ALIEN_STATE_LEFT_RIGHT =90;
    
    
    public float walkTime=0;
    boolean atEdge= false;
    int direction;
	public Alien(float x, float y, int direction) {
		super(x, y, ALIEN_WIDTH, ALIEN_HEIGHT);
		this.direction = direction;
		if(direction ==0)
			velocity.set(0, ALIEN_VELOCITY);
		else
			velocity.set(ALIEN_VELOCITY, 0);
		// TODO Auto-generated constructor stub
	}
	
	public void update(float deltaTime){
		position.add(velocity.x*deltaTime, velocity.y*deltaTime);
		bounds.lowerLeft.set(position).sub(bounds.width/2, bounds.height/2);
		
		if(atEdge){
			velocity.set(-1*velocity.x,-1*velocity.y);
			atEdge=false;
		}
		
		walkTime +=deltaTime;
	}

}
