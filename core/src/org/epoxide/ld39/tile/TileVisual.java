package org.epoxide.ld39.tile;

import org.epoxide.ld39.client.render.TileLayer;

public class TileVisual extends Tile {

    @Override
    public boolean shouldRenderLayer(TileLayer layer) {
        return layer == TileLayer.LAYER_TILE_FOREGROUND;
    }
}
