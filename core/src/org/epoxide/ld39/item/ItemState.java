package org.epoxide.ld39.item;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.epoxide.ld39.client.render.*;

public class ItemState {
    public static final ItemState EMPTY = new ItemState(null, 0,0);
    private final Item item;
    private final float x, y;
    
    public ItemState(Item item, float x, float y) {
        this.item = item;
        this.x = x;
        this.y = y;
    }
    
    public void renderItem (SpriteBatch batch, float renderX, float renderY, float tileWidth) {
        batch.draw(RenderManager.ITEM_TEXTURE, renderX, renderY, tileWidth, tileWidth, this.item.u2, this.item.v2, this.item.u, this.item.v);
    }
    
    public boolean isEmpty(){
        return item == null;
    }
    public Item getItem() {
        return item;
    }
    
    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }
}
