package org.epoxide.ld39.entity;

import org.epoxide.ld39.GameObject;
import org.epoxide.ld39.world.Direction;
import org.epoxide.ld39.world.ICollider;
import org.epoxide.ld39.world.World;

public abstract class Entity extends GameObject implements ICollider{

    public float x;
    public float y;
    public double motionX, motionY;
    public Direction rotation;
    public World world;

    public Entity (World world) {
        this.world = world;
    }
    
    
    public void update(){

    }
}
