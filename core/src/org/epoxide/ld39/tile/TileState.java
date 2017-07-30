package org.epoxide.ld39.tile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.epoxide.ld39.client.render.RenderManager;
import org.epoxide.ld39.client.render.TileLayer;

public class TileState {

    public final Tile tile;
    private final float x, y;

    public TileState(Tile tile, float x, float y) {
        this.tile = tile;
        this.x = x;
        this.y = y;
    }

    public Tile getTile() {
        return tile;
    }

    public boolean shouldRenderLayer(TileLayer layer) {
        return this.tile.shouldRenderLayer(layer);
    }

    public void renderTile(SpriteBatch batch, float renderX, float renderY, float tileWidth, TileLayer layer) {
        batch.draw(RenderManager.TILE_TEXTURE, renderX, renderY, tileWidth, tileWidth, tile.u2, tile.v2, tile.u, tile.v);
    }
}
