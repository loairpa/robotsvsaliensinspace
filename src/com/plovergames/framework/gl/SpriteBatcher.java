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

import javax.microedition.khronos.opengles.GL10;

import android.util.FloatMath;

import com.plovergames.framework.impl.GLGraphics;
import com.plovergames.framework.math.Vector2;

public class SpriteBatcher {
	
	final float[] verticesBuffer;
	int bufferIndex;
	final Vertices vertices;
	int numSprites;
	
	public SpriteBatcher(GLGraphics glGraphics, int maxSprites){
		this.verticesBuffer = new float [maxSprites*4*4];
		this.vertices = new Vertices(glGraphics, maxSprites*4, maxSprites*6, false, true);
		this.bufferIndex = 0;
		this.numSprites =0;
		
		short[] indices = new short[maxSprites*6];
		int len = indices.length;
		short j=0;
		
		for(int i = 0; i<len; i+=6, j+=4){
			indices[i+0] = (short)(j+0);
			indices[i+1] = (short)(j+1);
			indices[i+2] = (short)(j+2);
			indices[i+3] = (short)(j+2);
			indices[i+4] = (short)(j+3);
			indices[i+5] = (short)(j+0);
		}
		vertices.setIndices(indices, 0, len);
		
	}

	public void beginBatch(Texture texture){
		texture.bind();
		numSprites =0;
		bufferIndex =0;
	}
	
	public void endBatch(){
		vertices.setVertices(verticesBuffer, 0, bufferIndex);
		vertices.bind();
		vertices.draw(GL10.GL_TRIANGLES, 0, numSprites*6);
		vertices.unbind();
	}
	 public void drawSprite(float x, float y, float width, float height, TextureRegion region) {
	        float halfWidth = width / 2;
	        float halfHeight = height / 2;
	        float x1 = x - halfWidth;
	        float y1 = y - halfHeight;
	        float x2 = x + halfWidth;
	        float y2 = y + halfHeight;
	        
	        verticesBuffer[bufferIndex++] = x1;
	        verticesBuffer[bufferIndex++] = y1;
	        verticesBuffer[bufferIndex++] = region.u1;
	        verticesBuffer[bufferIndex++] = region.v2;
	        
	        verticesBuffer[bufferIndex++] = x2;
	        verticesBuffer[bufferIndex++] = y1;
	        verticesBuffer[bufferIndex++] = region.u2;
	        verticesBuffer[bufferIndex++] = region.v2;
	        
	        verticesBuffer[bufferIndex++] = x2;
	        verticesBuffer[bufferIndex++] = y2;
	        verticesBuffer[bufferIndex++] = region.u2;
	        verticesBuffer[bufferIndex++] = region.v1;
	        
	        verticesBuffer[bufferIndex++] = x1;
	        verticesBuffer[bufferIndex++] = y2;
	        verticesBuffer[bufferIndex++] = region.u1;
	        verticesBuffer[bufferIndex++] = region.v1;
	        
	        numSprites++;
	    } 

	    public void drawSprite(float x, float y, float width, float height, float angle, TextureRegion region) {
	        float halfWidth = width / 2;
	        float halfHeight = height / 2;
	        
	        float rad = angle * Vector2.TO_RADIANS;
	        float cos = FloatMath.cos(rad);
	        float sin = FloatMath.sin(rad);
	                
	        float x1 = -halfWidth * cos - (-halfHeight) * sin;
	        float y1 = -halfWidth * sin + (-halfHeight) * cos;
	        float x2 = halfWidth * cos - (-halfHeight) * sin;
	        float y2 = halfWidth * sin + (-halfHeight) * cos;
	        float x3 = halfWidth * cos - halfHeight * sin;
	        float y3 = halfWidth * sin + halfHeight * cos;
	        float x4 = -halfWidth * cos - halfHeight * sin;
	        float y4 = -halfWidth * sin + halfHeight * cos;
	        
	        x1 += x;
	        y1 += y;
	        x2 += x;
	        y2 += y;
	        x3 += x;
	        y3 += y;
	        x4 += x;
	        y4 += y;
	        
	        verticesBuffer[bufferIndex++] = x1;
	        verticesBuffer[bufferIndex++] = y1;
	        verticesBuffer[bufferIndex++] = region.u1;
	        verticesBuffer[bufferIndex++] = region.v2;
	        
	        verticesBuffer[bufferIndex++] = x2;
	        verticesBuffer[bufferIndex++] = y2;
	        verticesBuffer[bufferIndex++] = region.u2;
	        verticesBuffer[bufferIndex++] = region.v2;
	        
	        verticesBuffer[bufferIndex++] = x3;
	        verticesBuffer[bufferIndex++] = y3;
	        verticesBuffer[bufferIndex++] = region.u2;
	        verticesBuffer[bufferIndex++] = region.v1;
	        
	        verticesBuffer[bufferIndex++] = x4;
	        verticesBuffer[bufferIndex++] = y4;
	        verticesBuffer[bufferIndex++] = region.u1;
	        verticesBuffer[bufferIndex++] = region.v1;
	        
	        numSprites++;
	    }
	
	
}
