package org.epoxide.ld39.entity;

import org.epoxide.ld39.world.Direction;
import org.epoxide.ld39.world.World;

public class Entity {

    public float x;
    public float y;
    public float moveX, moveY;
    public Direction rotation;
    public World world;

    public Entity (World world) {
        this.world = world;
    }
}
