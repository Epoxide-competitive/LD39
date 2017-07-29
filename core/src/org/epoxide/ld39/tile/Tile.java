package org.epoxide.ld39.tile;

import org.epoxide.commons.collections.BiMap;
import org.epoxide.commons.registry.Identifier;
import org.epoxide.commons.registry.NamedRegistry;
import org.epoxide.commons.registry.Registerable;
import org.epoxide.ld39.LD39;

public class Tile extends Registerable<Tile> {
    private static final NamedRegistry<Tile> REGISTRY = new NamedRegistry<Tile>();
    private static final BiMap<Tile, Integer> tileColorMap = new BiMap<Tile, Integer>();

    public static final Tile VOID = registerTile("void", new Tile(-1));

    public final int color;
    public float u, v, u2, v2;

    public Tile(int color) {
        this.color = color;
        tileColorMap.put(this, color);
    }

    public static Tile registerTile(String id, Tile tile) {
        tile.setIdentifier(new Identifier(LD39.ID, id));
        REGISTRY.registerValue(tile);
        return tile;
    }

    protected Tile setUV(float u, float v, float u2, float v2) {

        this.u = u;
        this.v = v;
        this.u2 = u2;
        this.v2 = v2;
        return this;
    }

    public static Tile getTile(int color) {
        return tileColorMap.getInverse().get(color);
    }
}
