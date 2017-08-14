package org.epoxide.ld39;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import org.epoxide.commons.registry.NamedRegistry;
import org.epoxide.ld39.entity.Entity;
import org.epoxide.ld39.util.Rect;

import java.rmi.registry.Registry;

public class RenderManager {
    public final NamedRegistry<IRenderable> RENDER_REGISTRY = new NamedRegistry<>();
    public SpriteBatch batch;
    private Rect screen;

    public RenderManager()
    {
        screen = new Rect();
    }
    public void render()
    {
        
    }
}
