package org.epoxide.ld39.entity.components;

import com.badlogic.gdx.graphics.Texture;
import org.epoxide.commons.registry.Identifier;
import org.epoxide.ld39.entity.EntityComponent;

public class Sprite extends EntityComponent {
    public Sprite()
    {
        super(new Identifier("EntityComponent","Sprite"));
    }
    public Sprite(Texture texture) {
        super(new Identifier("EntityComponent","Sprite"));
    }
}
