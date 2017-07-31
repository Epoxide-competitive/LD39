package org.epoxide.ld39.entity;

import org.epoxide.ld39.world.World;

public class EntityPlayer extends Entity {

    private final int movementDelayDefault = 10;
    private int movementDelay;

    public float power = 500;
    public float maxPower = 500;

    public EntityPlayer (World world) {

        super(world);
        this.x = world.getMapWidth() / 2;
        this.y = world.getMapHeight() / 2;
        this.movementDelay = this.movementDelayDefault;
    }

    public int getMovementDelay () {

        return this.movementDelay;
    }

    public void setMovementDelay (int movementDelay) {

        this.movementDelay = movementDelay;
    }

    public void resetMovementDelay () {

        this.movementDelay = this.movementDelayDefault;
    }
}
