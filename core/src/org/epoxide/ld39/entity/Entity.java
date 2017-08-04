package org.epoxide.ld39.entity;

import org.epoxide.commons.registry.IRegisterable;
import org.epoxide.commons.registry.Identifier;

import java.util.ArrayList;
import java.util.List;

public class Entity implements IRegisterable<Entity> {
    private List<EntityComponent> components = null;
    private Identifier identifier = null;
    public Entity(String id)
    {
        identifier = new Identifier("Entity",id);
        components = new ArrayList<>();
    }
    public void update(float delta)
    {
        for (EntityComponent c: components) {
            c.update(this, delta);
        }
    }
    public boolean addComponent(EntityComponent c)
    {
        //boolean so we can notify if the add failed by returning false
        if(c != null) {
            components.add(c);
            return true;
        }
        return false;
    }
    public boolean removeComponent(EntityComponent c)
    {
        //boolean so we can notify if the add failed by returning false
        if(c != null) {
            if(components.contains(c)) {
                components.remove(c);
                return true;
            }
        }
        return false;
    }

    @Override
    public Entity setIdentifier(Identifier identifier) {
        return null;
    }

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }
}
