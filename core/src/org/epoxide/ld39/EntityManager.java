package org.epoxide.ld39;

import org.epoxide.commons.registry.NamedRegistry;
import org.epoxide.ld39.entity.Entity;
import org.epoxide.ld39.entity.EntityComponent;

public class EntityManager {
    public static final NamedRegistry<Entity> ENTITY_REGISTRY = new NamedRegistry<>();
    public static final NamedRegistry<? extends EntityComponent> COMPONENT_REGISTRY = new NamedRegistry<>();

    public void update(float delta)
    {

    }
}