package org.epoxide.ld39.entity;

import org.epoxide.ld39.item.ItemState;
import org.epoxide.ld39.world.World;

public abstract class EntityItem extends Entity {
    private final ItemState itemState;
    
    public EntityItem(World world, ItemState itemState) {
        super(world);
        this.itemState = itemState;
    }
    public EntityItem(World world, ItemState itemState, float x, float y) {
        super(world);
        this.itemState = itemState;
        this.x = x;
        this.y = y;
    }
    
    public ItemState getItemState() {
        return itemState;
    }
}
