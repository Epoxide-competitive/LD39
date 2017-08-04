package org.epoxide.ld39.entity;

import org.epoxide.commons.registry.IRegisterable;
import org.epoxide.commons.registry.Identifier;

public abstract class EntityComponent implements IRegisterable<EntityComponent> {
    private final Identifier identifier;
    private boolean active = false;
    public EntityComponent(String identifier, Object...args) {
        this.identifier = new Identifier("EntityComponent",identifier);
        //TODO parse args which apply to all components
    }
    public void update(Entity entity, float delta) {

    }
    public void onCreate(){
    }
    public void onDestroy(){
    }
    public boolean isActive(){return active;}
    public void setActive(boolean to){
        active = to;
    }
    @Override
    public Identifier getIdentifier() {
        return identifier;
    }
}