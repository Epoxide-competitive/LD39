package org.epoxide.ld39.entity;

import org.epoxide.ld39.world.World;

public class EntityPlayer extends Entity {

    public float power = 500;
    public float maxPower = 500;
    
    public EntityPlayer(World world) {
        super(world);
        this.x = world.getMapWidth()/2;
        this.y = world.getMapHeight()/2;
    }

}
