package com.plovergames.framework.gl;

public class Animation {
	public static final int ANIMATION_LOOPING =0;
	public static final int ANIMATION_NONLOOPING =1;
	
	final TextureRegion[] keyframes;
	final float frameDuration;
	
	public Animation(float frameDuration, TextureRegion ... keyFrames){
		this.frameDuration = frameDuration;
		this.keyframes = keyFrames;
	}
	
	public TextureRegion getKeyFrames(float stateTime, int mode){
		int frameNumber = (int) (stateTime/frameDuration);
		
		if(mode ==ANIMATION_NONLOOPING)
			frameNumber = Math.min(keyframes.length-1, frameNumber);
		else
			frameNumber = frameNumber % keyframes.length;
		
		return keyframes[frameNumber];
		
	}

}
