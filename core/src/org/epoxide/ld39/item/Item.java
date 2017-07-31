package org.epoxide.ld39.item;

import org.epoxide.commons.registry.*;
import org.epoxide.ld39.LD39;

public class Item  extends Registerable<Item>{
    public static final NamedRegistry<Item> REGISTRY = new NamedRegistry<>();
    
    public static final Item PICK = registerItem("pick", new Item().setUV(0,0,1,1));
    
    public float u, v, u2, v2;
    
    protected Item setUV (int u, int v, int u2, int v2) {
        
        return setUV((float)u * 0.0625f, (float) v * 0.0625f, (float) u2 * 0.0625f, (float) v2 * 0.0625f);
    }
    
    protected Item setUV (float u, float v, float u2, float v2) {
        
        this.u = u;
        this.v = v;
        this.u2 = u2;
        this.v2 = v2;
        return this;
    }
    
    public static Item registerItem (String id, Item item) {
        item.setIdentifier(new Identifier(LD39.ID, id));
        REGISTRY.registerValue(item);
        return item;
    }
}
