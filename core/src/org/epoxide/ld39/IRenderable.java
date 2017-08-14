package org.epoxide.ld39;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import org.epoxide.commons.registry.IRegisterable;

public interface IRenderable {
    default void render(SpriteBatch batch, Vector2 screenPos, float delta){return;}
}
