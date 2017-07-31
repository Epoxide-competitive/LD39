package org.epoxide.ld39.world;

import java.util.Map.Entry;
import java.util.Random;

import org.epoxide.ld39.tile.Tile;
import org.epoxide.ld39.tile.TileState;
import org.epoxide.ld39.tile.TileStateTorch;

public class World {

    private final TileState[][] tileMap;
    private final int width;
    private final int height;

    public World (int width, int height) {
        this.width = width;
        this.height = height;
        this.tileMap = new TileState[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.tileMap[i][j] = Math.random() > 0.99 ? new TileStateTorch(Tile.FLOOR, i, j) : new TileState(Tile.FLOOR, i, j);
            }
        }
        
    }

    public World (int[][] map) {
        this.width = map.length;
        this.height = map[0].length;
        this.tileMap = new TileState[this.width][this.height];

        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {

                Tile genTile = Tile.REGISTRY.getRandomValue(new Random());
                
                if (genTile == Tile.VOID || genTile.isCollidable())
                    genTile = Tile.WALL;
                this.tileMap[i][j] = map[i][j] == 1 ? new TileState(genTile, i, j) : new TileState(Tile.FLOOR, i, j);
                if (map[i][j] != 1 && Math.random() > 0.99) {
                    this.tileMap[i][j] = new TileStateTorch(Tile.FLOOR, i, j);
                }
            }
        }
    }

    public int getMapWidth () {

        return this.width;
    }

    public int getMapHeight () {

        return this.height;
    }

    public TileState getTileState (int x, int y) {

        if (x < 0 || y < 0 || x >= this.tileMap.length || y >= this.tileMap[0].length) {
            
            // Pass a fake TileState, since too many places expect non null. 
            return new TileState(Tile.VOID, 0f, 0f);
        }
        
        return this.tileMap[x][y] == null ? new TileState(Tile.VOID, x, y) : this.tileMap[x][y];
    }
}
