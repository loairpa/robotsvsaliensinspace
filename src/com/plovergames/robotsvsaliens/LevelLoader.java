package com.plovergames.robotsvsaliens;


import java.io.IOException;
import java.io.InputStream;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.Document;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.plovergames.framework.FileIO;
import com.plovergames.framework.Game;

import android.util.Log;
import android.util.Xml;

public class LevelLoader {
	 
	 FileIO fileIO;
	 String fileName;
	 InputStream in;
	 XmlPullParser parser;

	 DocumentBuilderFactory dbFactory;
	 DocumentBuilder dBuilder ;
	 Document doc ;
	 private static final String ns = null;

	 public LevelLoader(Game game, String fileName) throws XmlPullParserException {
		 this.fileIO = game.getFileIO();
		 this.fileName = fileName;
		 this.in = null;	 
	 }
	 
	public void loadLevel(List <Ship> ship, Robot robot, SelfDestructButton button, List <Alien> aliens, 
							List <Laser> lasers, List <Airlock> airlocks, List<Conveyorbelt> conveyorbelt, List<Instructions> instructions) throws XmlPullParserException, IOException{
		
		in = fileIO.readAsset(fileName);
		 try {
	            XmlPullParser parser = Xml.newPullParser();
	            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
	            parser.setInput(in, null);
	            parser.nextTag();

		 parser.require(XmlPullParser.START_TAG, ns, "level");
		 
		 while (parser.next() != XmlPullParser.END_TAG) {
			
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        // Starts by looking for the entry tag
	        if (name.equals("ship")) 
	            ship.add(readShippart(parser));
	        else if(name.equals("robot"))
	        	readRobot(parser, robot);
	        else if(name.equals("button"))
	        	readButton(parser, button);
	        else if(name.equals("alien"))
	        	aliens.add(readAlien(parser));
	        else if (name.equals("laser"))
	        	lasers.add(readLaser(parser));
	        else if (name.equals("airlock"))
	        	airlocks.add(readAirlock(parser));
	        else if(name.equals("conveyorbelt"))
	        	conveyorbelt.add(readConveyorbelt(parser));
	        else if (name.equals("instructions"))
	        	instructions.add(readInstructions(parser));
        else 
	            skip(parser);
	        
		 }}
		 finally {
	            in.close();
	            }

	}

private Ship readShippart(XmlPullParser parser) throws XmlPullParserException, IOException{
	parser.require(XmlPullParser.START_TAG, ns, "ship");
	Ship shippart; 
	float x =0.0f;
	float y = 0.0f;
	while (parser.next() != XmlPullParser.END_TAG) {
	     if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        if (name.equals("x")) {
	        	x = readFloat(parser,name);
	        } else if (name.equals("y")) {
	        	y = readFloat(parser,name);
	        } else {
	            skip(parser);
	        }

	}
	shippart = new Ship(x,y);
	
	
	return shippart;
}



private void readRobot(XmlPullParser parser,Robot robot) throws XmlPullParserException, IOException{
parser.require(XmlPullParser.START_TAG, ns, "robot");
float x =0.0f;
float y = 0.0f;
int direction = 180; 
while (parser.next() != XmlPullParser.END_TAG) {
    if (parser.getEventType() != XmlPullParser.START_TAG) {
           continue;
       }
       String name = parser.getName();
       if (name.equals("x")) {
       	x = readFloat(parser,name);
       } else if (name.equals("y")) {
       	y = readFloat(parser,name);
       } else if (name.equals("direction")){
    	   direction=readInt(parser,name);
       }
       else {
           skip(parser);
       }

}
robot.position.set(x+0.1f, y);
robot.bounds.lowerLeft.set(robot.position).sub(robot.bounds.width/2, robot.bounds.height/2);
robot.direction = direction;


}
private void readButton(XmlPullParser parser,SelfDestructButton button) throws XmlPullParserException, IOException{
parser.require(XmlPullParser.START_TAG, ns, "button");
float x =0.0f;
float y = 0.0f;
while (parser.next() != XmlPullParser.END_TAG) {
    if (parser.getEventType() != XmlPullParser.START_TAG) {
           continue;
       }
       String name = parser.getName();
       if (name.equals("x")) {
       	x = readFloat(parser,name);
       } else if (name.equals("y")) {
       	y = readFloat(parser,name);
       } else {
           skip(parser);
       }

}
button.position.set(x, y);
button.bounds.lowerLeft.set(button.position).sub(button.bounds.width/2, button.bounds.height/2);

}

private Alien readAlien(XmlPullParser parser) throws XmlPullParserException, IOException{
	parser.require(XmlPullParser.START_TAG, ns, "alien");
	
	float x =0.0f;
	float y = 0.0f;
	int state =0;
	while (parser.next() != XmlPullParser.END_TAG) {
	     if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        if (name.equals("x")) {
	        	x = readFloat(parser,name);
	        } else if (name.equals("y")) {
	        	y = readFloat(parser,name);
	        }else if(name.equals("state")){
	        	state = readInt(parser,name);
	        }
	        else {
	            skip(parser);
	        }

	}
	return new Alien(x,y,state);
}

private Laser readLaser(XmlPullParser parser) throws XmlPullParserException, IOException{
	parser.require(XmlPullParser.START_TAG, ns, "laser");
	
	float x =0.0f;
	float y = 0.0f;
	int direction =0;
	int length =0;
	while (parser.next() != XmlPullParser.END_TAG) {
	     if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        if (name.equals("x")) {
	        	x = readFloat(parser,name);
	        } else if (name.equals("y")) {
	        	y = readFloat(parser,name);
	        }else if(name.equals("direction")){
	        	direction = readInt(parser,name);
	        } else if(name.equals("length"))
	        	length = readInt(parser,name);
	        else {
	            skip(parser);
	        }

	}
	return new Laser(x,y,direction,length);
}

private Airlock readAirlock(XmlPullParser parser) throws XmlPullParserException, IOException{
	parser.require(XmlPullParser.START_TAG, ns, "airlock");
	
	float x =0.0f;
	float y = 0.0f;

	while (parser.next() != XmlPullParser.END_TAG) {
	     if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        if (name.equals("x")) {
	        	x = readFloat(parser,name);
	        } else if (name.equals("y")) {
	        	y = readFloat(parser,name);
	        }
	        else {
	            skip(parser);
	        }

	}
	return new Airlock(x,y);
}

private Conveyorbelt readConveyorbelt(XmlPullParser parser) throws XmlPullParserException, IOException{
	parser.require(XmlPullParser.START_TAG, ns, "conveyorbelt");
	
	float x =0.0f;
	float y = 0.0f;
	int dir = 0;
	while (parser.next() != XmlPullParser.END_TAG) {
	     if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        if (name.equals("x")) {
	        	x = readFloat(parser,name);
	        } else if (name.equals("y")) {
	        	y = readFloat(parser,name);
	        } else if(name.equals("dir"))
	        	dir = readInt(parser,name);
	        else {
	            skip(parser);
	        }

	}
	return new Conveyorbelt(x,y,dir);
}

private Instructions readInstructions(XmlPullParser parser) throws XmlPullParserException, IOException{
	parser.require(XmlPullParser.START_TAG, ns, "instructions");
	float x =0.0f;
	float y = 0.0f;
	String string = "";
	while (parser.next() != XmlPullParser.END_TAG) {
	     if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        if (name.equals("x")) {
	        	x = readFloat(parser,name);
	        } else if (name.equals("y")) {
	        	y = readFloat(parser,name);
	        } else if(name.equals("text")){
	        	string = readString(parser,name);
	        Log.d("Loading Instructions",string);}
	        else {
	            skip(parser);
	        }

	}
	return new Instructions(x,y,string);
}

private float readFloat(XmlPullParser parser, String label) throws XmlPullParserException, IOException{
	float result = 0.0f; 
	parser.require(XmlPullParser.START_TAG, ns, label);

    if (parser.next() == XmlPullParser.TEXT) {
        result = Float.parseFloat(parser.getText());
        parser.nextTag();
    }
    parser.require(XmlPullParser.END_TAG, ns, label);

    return result;

}
private int readInt(XmlPullParser parser, String label) throws XmlPullParserException, IOException{
	int result =0; 
	parser.require(XmlPullParser.START_TAG, ns, label);

    if (parser.next() == XmlPullParser.TEXT) {
        result = Integer.parseInt(parser.getText());
        parser.nextTag();
    }
    parser.require(XmlPullParser.END_TAG, ns, label);

    return result;

}

private String readString(XmlPullParser parser, String label) throws XmlPullParserException, IOException{
	String string = ""; 
	parser.require(XmlPullParser.START_TAG, ns, label);

    if (parser.next() == XmlPullParser.TEXT) {
        string = parser.getText();
        parser.nextTag();
    }
    parser.require(XmlPullParser.END_TAG, ns, label);

    return string;

}
private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
    if (parser.getEventType() != XmlPullParser.START_TAG) {
        throw new IllegalStateException();
    }
    int depth = 1;
    while (depth != 0) {
        switch (parser.next()) {
        case XmlPullParser.END_TAG:
            depth--;
            break;
        case XmlPullParser.START_TAG:
            depth++;
            break;
        }
    }
 }
}
