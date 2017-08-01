package org.epoxide.ld39.entity;

import org.epoxide.ld39.world.ICollider;
import org.epoxide.ld39.world.World;

import java.util.Random;

public class EntityPlayer extends Entity implements IDamageable, ICollider {

    public float power = 100;
    public float maxPower = 100;
    private final double movementSpeed = 0.05;
    
    public EntityPlayer (World world) {

        super(world);
        randomizeLocation(world);
    }
    
    
    @Override
    public void update() {
        super.update();
        if(motionX > 0) {
            this.x += movementSpeed;
            motionX -= movementSpeed;
            if(motionX <= 0) {
                motionX = 0;
            }
        } else if(motionX < 0) {
            this.x -= movementSpeed;
            motionX += movementSpeed;
            if(motionX >= 0) {
                motionX = 0;
            }
        }
    
        if(motionY > 0) {
            this.y += movementSpeed;
            motionY -= movementSpeed;
            if(motionY <= 0) {
                motionY = 0;
            }
        } else if(motionY < 0) {
            this.y -= movementSpeed;
            motionY += movementSpeed;
            if(motionY >= 0) {
                motionY = 0;
            }
        }
        if(!this.hasMotion()) {
            this.x = Math.round(x);
            this.y = Math.round(y);
        }
    }
    
    public double getMotionX() {
        return motionX;
    }
    
    public void setMotionX(double motionX) {
        this.motionX = motionX;
    }
    
    public double getMotionY() {
        return motionY;
    }
    
    public void setMotionY(double motionY) {
        this.motionY = motionY;
    }
    
    public void addMotionX(double motionX){
        this.motionX+= motionX;
    }
    public void addMotionY(double motionY){
        this.motionY+= motionY;
    }
    
    private void randomizeLocation(World world){
        Random rand = new Random();
        while(true){
            int randX = rand.nextInt(world.getMapWidth());
            int randY = rand.nextInt(world.getMapHeight());
            if(world.checkClear(randX,randY)){
                continue;
            }
            this.x = randX;
            this.y = randY;
            break;
        }
        
    }
    
    public boolean hasMotion(){
        return hasMotionX() ||hasMotionY();
    }
    public boolean hasMotionX(){
        return motionX !=0;
    }
    public boolean hasMotionY(){
        return motionY !=0;
    }

    @Override
    public void takeDamage(float amount) {
        power -= amount;
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
