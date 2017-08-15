package org.epoxide.ld39.world;

import java.util.Map;

public class World {
    private String seed = "315462187979456189456189765184715484882151516";
    private Chunk[] loadedChunks = new Chunk[chunksInMemory];
    public static final int chunksInMemory = 25;
    private Map<Integer,Integer> generatedChunks;
}
