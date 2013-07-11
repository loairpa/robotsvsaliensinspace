package com.plovergames.robotsvsaliens;

import java.io.IOException;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.xmlpull.v1.XmlPullParserException;

import com.plovergames.framework.Screen;
import com.plovergames.framework.impl.GLGame;
import com.plovergames.robotsvsaliens.Assets;
import com.plovergames.robotsvsaliens.MainMenuScreen;
import com.plovergames.robotsvsaliens.Settings;

public class RobotsVsAliens extends GLGame {

	boolean firstTimeCreate = true;
	@Override
	public Screen getStartScreen() {
		// TODO Auto-generated method stub
		return new MainMenuScreen(this);
	}

	
	public void onSurfaceCreated(GL10 gl, EGLConfig config){
		super.onSurfaceCreated(gl, config);
		if(firstTimeCreate){
			try{
			Settings.load(getFileIO());
			Assets.load(this);
			firstTimeCreate = false;
			}catch(XmlPullParserException e){
	              throw new RuntimeException("Couldn't load texture '" +e);
	          }
		}
		else
			Assets.reload();
	}
	
	@Override
	public void onPause(){
		super.onPause();


	}
}

