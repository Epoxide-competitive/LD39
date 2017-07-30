package org.epoxide.ld39.client.render.lighting;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import java.util.ArrayList;
import java.util.List;

import org.epoxide.ld39.LD39;

public class LightMap {

    public static final Texture LIGHT_SPRITE = new Texture("assets/ld39/textures/misc/lightmap.png");
    
    private ShaderProgram lightShader;
    private FrameBuffer lightBuffer;
    private float width = 1;
    private float height = 1;
    private List<ILight> lights = new ArrayList<>();

    public LightMap() {
    	
        this.lightShader = new ShaderProgram(Gdx.files.internal("assets/ld39/shaders/main.vert"), Gdx.files.internal("assets/ld39/shaders/light.frag"));
        
        lightShader.begin();
        lightShader.setUniformf("ambientColor", 0.3f, 0.38f, 0.4f, 0.25f);
        lightShader.setUniformi("u_lightmap", 1);
        lightShader.end();
    }
    
    public void adjustSize(int width, int height) {

        this.width = width;
        this.height = height;

        if (this.lightBuffer != null) {

            this.lightBuffer.dispose();
        }

        lightBuffer = new FrameBuffer(Format.RGBA8888, (int) this.width, (int) this.height, false);
        lightBuffer.getColorBufferTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        
        this.lightShader.begin();
        this.lightShader.setUniformf("resolution", width, height);
        this.lightShader.end();
    }

    public void render(SpriteBatch batch, float delta) {
    	
        lightBuffer.begin();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for (ILight source : this.lights) {

            batch.begin();

            // Sets the light's color
            batch.setColor(source.getColor());

            // The position of the light on screen.
            float lightX = source.getXPos();
            float lightY = source.getYPos();

            // The strength of the light
            final float strength = source.getStrength(delta);

            // Offset for strength
            lightX -= strength / 2;
            lightY -= strength / 2;

            batch.draw(source.getLightTexture(), lightX, lightY, strength, strength);
            batch.end();

        }

        lightBuffer.end();
        this.lights.clear();
        
        //TODO Make a better way to reset all of this, preferably reusable.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setColor(Color.WHITE);
        batch.setProjectionMatrix(LD39.instance.getCamera().combined);
        batch.setShader(this.lightShader);
        this.resetBinds();
    }

    public void resetBinds() {
    	
        this.lightBuffer.getColorBufferTexture().bind(1);
        LIGHT_SPRITE.bind(0);
    }

    public void addLight(float renderX, float renderY, float strength, Color color) {
    	
        this.lights.add(new LightBase(renderX, renderY, strength, color));
    }
}
