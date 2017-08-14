package org.epoxide.ld39.entity;

import org.epoxide.commons.registry.Identifier;
import org.epoxide.commons.registry.RegisterableClass;

public abstract class EntityComponent extends RegisterableClass<EntityComponent> {
    private boolean active = false;
    public void update(Entity entity, float delta) {

    }
    protected void onCreate(){
    }
    protected void onDestroy(){
    }
    protected void onActivate(){
    }
    public boolean isActive(){return active;}
    public void setActive(boolean to){
        active = to;
        if(active)
            onActivate();
    }
    public EntityComponent(Identifier identifier)
    {
        super(EntityComponent.class);
        setIdentifier(identifier);
        registerWithManagers();
        //TODO implement events correctly in a Manager class
        /*onCreate();
        if(isActive())
            onActivate();*/
    }
    @Override
    public Identifier getIdentifier() {
        return getIdentifier();
    }
    @Override
    public EntityComponent setIdentifier(Identifier identifier) {//this is set at object creation, we should never reset it
        return this;
    }
    public void destroy(Object...args)
    {
        onDestroy();
        //references should be cleaned up
        //deregister
        deregisterFromManagers();
        //system.gc kek
    }
    protected void registerWithManagers()
    {

    }
    protected void deregisterFromManagers()
    {

    }
    @Override
    public EntityComponent construct(Object... args) {
        return super.construct(args);
    }
}