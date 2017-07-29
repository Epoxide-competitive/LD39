package org.epoxide.ld39.world;

import org.epoxide.ld39.tile.Tile;

public class World {

    private Tile[][] tileMap;
    private int worldWidth;
    private int worldHeight;


    public Tile[][] getTileMap() {
        return tileMap;
    }

    public void setTileMap(Tile[][] tileMap) {
        this.tileMap = tileMap;
    }

    public int getMapWidth() {
        return this.tileMap.length;
    }

    public int getMapHeight() {
        if (this.getMapWidth() == 0)
            return 0;
        return this.tileMap[0].length;
    }
}
