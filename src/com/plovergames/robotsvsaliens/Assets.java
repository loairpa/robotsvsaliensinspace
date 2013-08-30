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
package com.plovergames.robotsvsaliens;



import org.xmlpull.v1.XmlPullParserException;

import com.plovergames.framework.Music;
import com.plovergames.framework.Sound;
import com.plovergames.framework.gl.Animation;
import com.plovergames.framework.gl.Font;
import com.plovergames.framework.gl.Texture;
import com.plovergames.framework.gl.TextureRegion;
import com.plovergames.framework.impl.GLGame;

public class Assets {


	public static Texture background;
    public static TextureRegion backgroundRegion;
    
    public static Texture items;        
    public static TextureRegion mainMenu;
    public static TextureRegion selfdestructbutton;
    public static TextureRegion shippart;
    public static TextureRegion laserbeam;
    public static TextureRegion laser;
    public static TextureRegion delete;
    public static TextureRegion pause;
    public static TextureRegion play;
    public static TextureRegion move;
    public static TextureRegion turnright;
    public static TextureRegion turnleft;
    public static TextureRegion wait;
    public static TextureRegion zero, one, two, three, four, five, six, seven, eight, nine;
    
    public static TextureRegion scroll; 
    
    public static TextureRegion airlock;    
    public static TextureRegion fire;
    public static TextureRegion controlpanel;
    public static TextureRegion active;
    public static TextureRegion inactive;
    public static TextureRegion robotStop;
    public static Animation robotDownAnim;
    public static Animation robotUpAnim;
    public static Animation robotSideAnim;
    public static Animation robotHitByLaser;
 
    public static Animation alienAnim;
    public static Animation conveyorbelt; 
    
    public static TextureRegion rotator;
    public static TextureRegion explosion;
    public static Font font;


    public static void load(GLGame game) throws XmlPullParserException {

        background = new Texture(game, "background.png");
        backgroundRegion = new TextureRegion(background, 0, 0, 320, 480);
        
        items = new Texture(game, "atlasnew.png");    
        
        mainMenu = new TextureRegion(items, 194, 98, 120,64);

        controlpanel= new TextureRegion(items, 0, 64, 320, 32);
        active = new TextureRegion(items,0,0,32,32);
        inactive = new TextureRegion(items,32,0,32,32);
        
        delete= new TextureRegion(items, 64, 0, 32, 32);
        play= new TextureRegion(items, 96, 0, 32, 32);
        pause= new TextureRegion(items, 128, 0, 32, 32);
        
        move= new TextureRegion(items, 0, 32, 32, 32);
        turnright= new TextureRegion(items, 32, 32, 32, 32);
        turnleft= new TextureRegion(items, 64, 32, 32, 32);       
        wait= new TextureRegion(items, 96, 32,32, 32);
        zero = new TextureRegion(items,160,32,32,32);
        one = new TextureRegion(items,192,32,32,32);
        two = new TextureRegion(items,192+32,32,32,32);
        three = new TextureRegion(items,192+64,32,32,32);
        four = new TextureRegion(items,192+96,32,32,32);
        five = new TextureRegion(items,160,0,32,32);
        six = new TextureRegion(items,192,0,32,32);
        seven= new TextureRegion(items,192+32,0,32,32);
        eight= new TextureRegion(items,192+64,0,32,32);
        nine= new TextureRegion(items,192+96,0,32,32);
        
        scroll = new TextureRegion(items,128,32,32,32);
 
        fire= new TextureRegion(items, 32, 160, 32, 32);
        shippart = new TextureRegion(items, 0, 160, 32, 32);      
        selfdestructbutton = new TextureRegion(items, 0, 96, 32, 33);
        laserbeam= new TextureRegion(items, 32, 128,32, 32);
        laser= new TextureRegion(items, 0, 128, 32, 32);
        airlock= new TextureRegion(items, 66, 126, 32, 34);   
        
        robotStop= new TextureRegion(items, 98,160,32,32);   
        robotDownAnim = new Animation(0.2f, new TextureRegion(items, 98,160,30,32),
				new TextureRegion(items,98+32,160,30,32), 
				new TextureRegion(items, 98,160,30,32),
				new TextureRegion(items,98+64,160,30,32));
        
        robotUpAnim = new Animation(0.2f, new TextureRegion(items, 98,128,30,32),
				new TextureRegion(items,98+32,128,30,32), 
				new TextureRegion(items, 98,128,30,32),
				new TextureRegion(items,98+64,128,30,32));
        
        
        robotSideAnim = new Animation(0.2f, new TextureRegion(items, 98,100,32,30),
				new TextureRegion(items,98+32,100,32,30), 
				new TextureRegion(items, 98,100,32,30),
				new TextureRegion(items,98+64,100,30,30));
        
        robotHitByLaser = new Animation(0.2f, new TextureRegion(items, 192, 160, 30, 32), 
        		new TextureRegion(items, 98,160,30,32));
        				
        
        alienAnim = new Animation(0.2f,                                 
              new TextureRegion(items, 32, 98, 32, 32),
              new TextureRegion(items, 64, 98, 32, 32));

        conveyorbelt = new Animation(0.2f,                                 
           new TextureRegion(items,61, 160, 16, 32),
             new TextureRegion(items, 81,160,  16, 32));
        
        rotator = new TextureRegion(items, 224,160,30,32);
        
        explosion = new TextureRegion(items, 256,160,32,32);

        font = new Font(items,8, 160+32, 16, 16, 20);
        
//        mainMenu = new TextureRegion(items, 0, 224, 300, 110);
//        pauseMenu = new TextureRegion(items, 224, 128, 192, 96);
//        ready = new TextureRegion(items, 320, 224, 192, 32);
//        gameOver = new TextureRegion(items, 352, 256, 160, 96);
//        highScoresRegion = new TextureRegion(Assets.items, 0, 257, 300, 110 / 3);
//        logo = new TextureRegion(items, 0, 352, 274, 142);
//        soundOff = new TextureRegion(items, 0, 0, 64, 64);
//        soundOn = new TextureRegion(items, 64, 0, 64, 64);
//        arrow = new TextureRegion(items, 0, 64, 64, 64);
//        pause = new TextureRegion(items, 64, 64, 64, 64);
//        

//        
//        music = game.getAudio().newMusic("music.mp3");
//        music.setLooping(true);
//        music.setVolume(0.5f);
//        if(Settings.soundEnabled)
//            music.play();
//        jumpSound = game.getAudio().newSound("jump.ogg");
//        highJumpSound = game.getAudio().newSound("highjump.ogg");
//        hitSound = game.getAudio().newSound("hit.ogg");
//        coinSound = game.getAudio().newSound("coin.ogg");
//        clickSound = game.getAudio().newSound("click.ogg");       
    }       

    public static void reload() {
        background.reload();
        items.reload();

    }
}
