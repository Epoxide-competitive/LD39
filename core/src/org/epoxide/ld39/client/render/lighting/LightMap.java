package org.epoxide.ld39.client.render.lighting;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

public class LightMap {

	public static final Texture LIGHT_SPRITE = new Texture("assets/ld39/textures/misc/lightmap.png");
    private FrameBuffer lightBuffer;
    private TextureRegion lightBufferRegion;
    private float width = 1;
    private float height = 1;

    public void adjustSize(int width, int height) {
    	
    	this.width = width;
    	this.height = height;
    	
    	if (this.lightBuffer != null) {
    		
    		this.lightBuffer.dispose();
    	}
    	
    	lightBuffer = new FrameBuffer(Format.RGBA8888, (int)this.height, (int)this.width, false);
    	lightBuffer.getColorBufferTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);    	
    	lightBufferRegion = new TextureRegion(lightBuffer.getColorBufferTexture(), 0, (int) (lightBuffer.getHeight() - this.width), (int) this.height, (int) this.width);
    	lightBufferRegion.flip(false, false);
    }
    
    public void render (SpriteBatch batch, float delta) {
    	
    	lightBuffer.begin();
    	
    	//Sets the ambient color
    	Gdx.gl.glClearColor(0.3f, 0.38f,  0.4f, 1);
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	 	
    	batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
    	batch.enableBlending();   	
    	
    	List<ILight> lights = new ArrayList<ILight>();
    	
    	lights.add(new LightBase(100f, 100f, 20f, Color.BLUE));
    	lights.add(new LightBase(100f, 200f, 20f, Color.GREEN));
    	lights.add(new LightBase(200f, 100f, 20f, Color.RED));
    	lights.add(new LightBase(200f, 200f, 20f, Color.YELLOW));
    	
    	for (ILight source : lights) {
    		
        	batch.begin(); 
        	
        	// Sets the light's color
        	batch.setColor(source.getColor());
        	
        	// The position of the light on screen. 
        	float lightX = source.getXPos();
        	float lightY = source.getYPos();

        	// The strength of the light
        	final float strength = source.getStrength(delta);
        	
        	// Offset for strength
        	lightX -= strength/2;
        	lightY -= strength/2;
        	
        	batch.draw(source.getLightTexture(), lightX, lightY, strength, strength, 0, 0, 128, 128, false, true);
        	batch.end();
        	
    	}
    	
    	lightBuffer.end();
    	
    	batch.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_ZERO);
    	batch.begin();
    	batch.draw(this.lightBufferRegion, 0f, 0f, this.width, this.height);
    	batch.end();
    }
}
