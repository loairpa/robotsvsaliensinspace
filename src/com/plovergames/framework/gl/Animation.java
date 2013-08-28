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
