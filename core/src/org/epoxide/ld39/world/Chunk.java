package org.epoxide.ld39.world;

import org.epoxide.ld39.entity.Entity;

import java.util.List;

public class Chunk {
    public static final int chunkSize = 256;//16x16
    public static final int depthMax = 4;//max number of tiles occupying one space
    private Tile[][] tiles = new Tile[chunkSize][depthMax];
    private List<Entity> entityList = null;
    private int x;
    private int y;
    public Chunk(int x, int y)
    {
        this.x=x;
        this.y=y;
    }
    public void generate()
    {
        //do procedural generation algorithm
        for(int i = 0; i < tiles.length; i++)
        {

        }
    }
}
