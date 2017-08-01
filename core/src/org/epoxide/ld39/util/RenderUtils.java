package org.epoxide.ld39.util;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderUtils {
    
    public static void renderMultilineString(SpriteBatch batch, BitmapFont font, List<String> lines, float x, float y, int lineHeight) {
        
        int offset = 0;
        
        for (String line : lines) {
            
            offset += lineHeight;
            
            font.draw(batch, line, x, y - offset);
        }
    }
}
