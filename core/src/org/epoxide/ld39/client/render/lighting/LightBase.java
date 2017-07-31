package org.epoxide.ld39.client.render.lighting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class LightBase implements ILight {

    private final float posX;
    private final float posY;
    private final float strength;
    private final Color color;

    public LightBase (float x, float y, float strength, Color color) {

        this.posX = x;
        this.posY = y;
        this.strength = strength;
        this.color = color;
    }

    @Override
    public float getStrength (float delta) {

        // (128 / 100f) * 1024;
        return 128 / 100f * (10 * this.strength);
    }

    @Override
    public float getXPos () {

        return this.posX;
    }

    @Override
    public float getYPos () {

        return this.posY;
    }

    @Override
    public Color getColor () {

        return this.color;
    }

    @Override
    public Texture getLightTexture () {

        return LightMap.LIGHT_SPRITE;
    }
}
