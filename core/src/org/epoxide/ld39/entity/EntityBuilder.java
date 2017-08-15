package org.epoxide.ld39.entity;

import org.epoxide.ld39.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class EntityBuilder {
    private String id = null;
    private List<EntityComponent> components = null;
    private List<Object[]> arguments = null;
    public EntityBuilder(String id)
    {
        this.id = id;
        components = new ArrayList<>();
        arguments = new ArrayList<>();
    }
    public EntityBuilder addComponent(EntityComponent e,Object...args)
    {
        if(e != null)
            components.add(e);
        return this;
    }
    public EntityBuilder addComponent(String identifier, Object...args)
    {
        components.add(EntityManager.COMPONENT_REGISTRY.getValue(identifier).construct(args));
        return this;
    }
    public Entity create()
    {
        return new Entity(id,components);
    }
}