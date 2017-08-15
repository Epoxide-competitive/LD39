package org.epoxide.ld39;

import org.epoxide.commons.registry.NamedRegistry;
import org.epoxide.ld39.entity.Entity;
import org.epoxide.ld39.entity.EntityComponent;
import org.epoxide.ld39.entity.components.Sprite;

import java.io.IOException;

public class EntityManager {
    public static final NamedRegistry<Entity> ENTITY_REGISTRY = new NamedRegistry<>();
    public static final NamedRegistry<EntityComponent> COMPONENT_REGISTRY = new NamedRegistry<>();
    private static final EntityComponent sprite = COMPONENT_REGISTRY.registerValue(new Sprite());
    //todo set up a component registry to query for component construction
    //todo call update on relevant Entities from this class
    void deserializeEntities() throws IOException
    {
        //TODO write json deserialization code to populate entity registry
    }
    void init(){
        
    }
    public void update(float delta)
    {
        ENTITY_REGISTRY.iterator().forEachRemaining(entity -> entity.update(delta));
    }
}