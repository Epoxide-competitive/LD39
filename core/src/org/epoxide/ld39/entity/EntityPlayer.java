package org.epoxide.ld39.entity;

import org.epoxide.ld39.world.World;

import java.util.Random;

public class EntityPlayer extends Entity {

    private final int movementDelayDefault = 20;
    private int movementDelay;

    public float power = 500;
    public float maxPower = 500;

    public EntityPlayer (World world) {

        super(world);
        randomizeLocation(world);
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
    
    
    private void randomizeLocation(World world){
        Random rand = new Random();
        while(true){
            int randX = rand.nextInt(world.getMapWidth());
            int randY = rand.nextInt(world.getMapHeight());
            if(world.getTileState(randX, randY).tile.isCollidable()){
                this.x = randX;
                this.y = randY;
                break;
            }
        }
        
    }
    
}
