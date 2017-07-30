package org.epoxide.ld39.entity;

import org.epoxide.ld39.world.World;

public class EntityPlayer extends Entity {

    public float power = 50;
    public float maxPower= 50;
    
    public EntityPlayer(World world) {
        super(world);
        this.x = world.getMapWidth()/2;
        this.y = world.getMapHeight()/2;
    }

}
