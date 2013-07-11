package com.plovergames.framework;

import com.plovergames.framework.math.Vector2;

public class DynamincGameObject extends GameObject {
	public final Vector2 velocity;
	public final Vector2 accel;
	
	public DynamincGameObject(float x, float y, float width, float height) {
		super(x, y, width, height);
		velocity= new Vector2();
		accel= new Vector2();

	}

	

}
