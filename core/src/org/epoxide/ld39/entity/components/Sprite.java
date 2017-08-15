package org.epoxide.ld39.entity.components;

import com.badlogic.gdx.graphics.Texture;
import org.epoxide.commons.registry.Identifier;
import org.epoxide.ld39.IRenderable;
import org.epoxide.ld39.entity.EntityComponent;

public class Sprite extends EntityComponent implements IRenderable {
    public Sprite()
    {
        super(new Identifier("EntityComponent","Sprite"));
    }
    public Sprite(Texture texture) {
        super(new Identifier("EntityComponent","Sprite"));
        this.texture = texture;
    }
    private Texture texture;
}
