package org.epoxide.ld39.tile;

import org.epoxide.commons.registry.Identifier;
import org.epoxide.commons.registry.NamedRegistry;
import org.epoxide.commons.registry.Registerable;
import org.epoxide.ld39.LD39;
import org.epoxide.ld39.client.render.TileLayer;

public class Tile extends Registerable<Tile> {
    private static final NamedRegistry<Tile> REGISTRY = new NamedRegistry<>();

    public static final Tile VOID = registerTile("void", new Tile());
    public static final Tile WALL = registerTile("wall", new Tile()).setUV(0, 0, 1, 1);
    public static final Tile FLOOR = registerTile("floor", new Tile()).setUV(1, 0, 2, 1).setCollidable(true);
    public static final Tile TORCH = registerTile("torch", new TileVisual()).setUV(4, 0, 5, 1).setCollidable(true);

    public float u, v, u2, v2;
    private boolean collidable = false;

    public static Tile registerTile (String id, Tile tile) {

        tile.setIdentifier(new Identifier(LD39.ID, id));
        REGISTRY.registerValue(tile);
        return tile;
    }
    
    protected Tile setUV (int u, int v, int u2, int v2) {
        
        return setUV(u * 0.062f, v * 0.0625f, u2 * 0.0625f, v2 * 0.0625f);
    }

    protected Tile setUV (float u, float v, float u2, float v2) {

        this.u = u;
        this.v = v;
        this.u2 = u2;
        this.v2 = v2;
        return this;
    }

    public boolean shouldRenderLayer (TileLayer layer) {

        return layer == TileLayer.LAYER_TILE_BACKGROUND;
    }

    public boolean isCollidable () {

        return this.collidable;
    }

    public Tile setCollidable (boolean collidable) {

        this.collidable = collidable;
        return this;
    }
}
