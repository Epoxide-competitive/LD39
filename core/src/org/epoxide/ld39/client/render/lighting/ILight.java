package org.epoxide.ld39.client.render.lighting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public interface ILight {

	float getStrength(float delta);
	
	float getXPos();
	
	float getYPos();
	
	Color getColor();
	
	Texture getLightTexture();
}