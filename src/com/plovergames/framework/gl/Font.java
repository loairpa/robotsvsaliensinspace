package com.plovergames.framework.gl;

import android.util.Log;

import com.plovergames.robotsvsaliens.World;

public class Font {
	public final Texture texture;
    public final int glyphWidth;
    public final int glyphHeight;
    public final TextureRegion[] glyphs = new TextureRegion[96];   


    public Font(Texture texture, 
            int offsetX, int offsetY,
            int glyphsPerRow, int glyphWidth, int glyphHeight) {        
        this.texture = texture;
        this.glyphWidth = glyphWidth;
        this.glyphHeight = glyphHeight;
        int x = offsetX;
        int y = offsetY;
        for(int i = 0; i < 96; i++) {
            glyphs[i] = new TextureRegion(texture, x, y, glyphWidth, glyphHeight);
            x += glyphWidth;
            if(x == offsetX + glyphsPerRow * glyphWidth) {
                x = offsetX;
                y += glyphHeight;
            }
        }        
    }

    public void drawText(SpriteBatcher batcher, String text, float x, float y) {
        int len = text.length();
        for(int i = 0; i < len; i++) {
            int c = text.charAt(i) - ' ';
            if(c < 0 || c > glyphs.length - 1) 
                continue;
            
            TextureRegion glyph = glyphs[c];
            batcher.drawSprite(x, y, glyphWidth, glyphHeight, glyph);
            x += glyphWidth;
        }
    }
    
    public void drawText(SpriteBatcher batcher, String text, float x, float y, float glyphWidth, float glyphHeight) {
        int len = text.length();
        float tempX = x;
  
        for(int i = 0; i < len; i++) {
            int c = text.charAt(i) - ' ';
            if(c < 0 || c > glyphs.length - 1) 
                continue;
            
            TextureRegion glyph = glyphs[c];
            batcher.drawSprite(x, y, glyphWidth, glyphHeight, glyph);
            x += glyphWidth;
            if(x >  World.WORLD_WIDTH){
            	x= tempX;
            	y -= glyphHeight;
            }
        }
    }
    

    public void animateText(SpriteBatcher batcher, String text, float x, float y, float glyphWidth, float glyphHeight, int currentLetter){

        float tempX = x;
        for(int i = 0; i < currentLetter; i++) {
            int c = text.charAt(i) - ' ';
            if(c < 0 || c > glyphs.length - 1) 
                continue;
            
            TextureRegion glyph = glyphs[c];
            batcher.drawSprite(x, y, glyphWidth, glyphHeight, glyph);
            x += glyphWidth;
            if(x >  World.WORLD_WIDTH){
            	x= tempX;
            	y -= glyphHeight;
            }

            
//            while(j<animationSpeed)j++;
        }
    }

}
