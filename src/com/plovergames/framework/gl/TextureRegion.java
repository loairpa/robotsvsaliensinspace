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

public class TextureRegion {
	public final float u1, u2, v1, v2;
	public final Texture texture;
	
	
	public TextureRegion(Texture texture, float x, float y, float width, float height){
		this.u1 = x/texture.width;
		this.v1 = y /texture.height;
		this.u2 = this.u1+width/texture.width;
		this.v2 = this.v1 + height/texture.height;
		this.texture = texture; 
		
	}

}
