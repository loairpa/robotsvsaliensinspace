package com.plovergames.framework.impl;

import com.plovergames.framework.Game;
import com.plovergames.framework.Screen;

public abstract class GLScreen extends Screen {
	protected final GLGraphics glGraphics;
	protected final GLGame glGame;
	
	public GLScreen(Game game){
		super(game);
		glGame = (GLGame)game;
		glGraphics = glGame.getGLGraphics();
	}

}
