package org.epoxide.ld39.world;

import org.epoxide.ld39.tile.Tile;
import org.epoxide.ld39.tile.TileState;
import org.epoxide.ld39.tile.TileStateTorch;

public class World {

    private TileState[][] tileMap;
    private int width;
    private int height;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.tileMap = new TileState[width][height];

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                this.tileMap[i][j] = Math.random() > 0.99 ? new TileStateTorch(Tile.FLOOR, i, j) : new TileState(Tile.FLOOR, i, j);

    }

    public World(int[][] map) {
        this.width = map.length;
        this.height = map[0].length;
        this.tileMap = new TileState[width][height];

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {

                this.tileMap[i][j] = map[i][j] == 1 ? new TileState(Tile.WALL, i, j) : new TileState(Tile.FLOOR, i, j);
                if (map[i][j] != 1 && Math.random() > 0.99) {
                    this.tileMap[i][j] = new TileStateTorch(Tile.FLOOR, i, j);
                }
            }
    }


    public int getMapWidth() {
        return this.width;
    }

    public int getMapHeight() {
        return this.height;
    }

    public TileState getTileState(int x, int y) {
        return this.tileMap[x][y] == null ? new TileState(Tile.VOID, x, y) : this.tileMap[x][y];
    }
}
