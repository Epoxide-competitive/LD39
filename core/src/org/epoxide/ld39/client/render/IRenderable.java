package org.epoxide.ld39.client.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Comparator;

public interface IRenderable {
    Comparator<IRenderable> RENDER_COMPARATOR = new Comparator<IRenderable>() {
        @Override
        public int compare(IRenderable r, IRenderable r1) {
            int c = r1.renderLayer() - r.renderLayer();
            if(c == 0)
                c = r1.renderPriority() - r.renderPriority();
            return c;
        }
    };
    default void render(SpriteBatch batch, Vector2 screenPos, float delta){return;}
    int renderLayer();
    int renderPriority();
    default boolean isOpaque(){return true;}//defaults to opaque
}
