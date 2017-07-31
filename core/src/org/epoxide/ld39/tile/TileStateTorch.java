package org.epoxide.ld39.tile;

import org.epoxide.ld39.LD39;
import org.epoxide.ld39.client.render.RenderManager;
import org.epoxide.ld39.client.render.TileLayer;
import org.epoxide.ld39.world.Direction;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileStateTorch extends TileDirectionState {
    private final Tile backgroundTile;

    public TileStateTorch (Tile backgroundTile, float x, float y) {
        super(Tile.TORCH, x, y);
        this.backgroundTile = backgroundTile;
        this.direction = Direction.NORTH;
    }

    @Override
    public boolean shouldRenderLayer (TileLayer layer) {

        return true;
    }

    @Override
    public void renderTile (SpriteBatch batch, float renderX, float renderY, float tileWidth, TileLayer layer) {

        if (layer == TileLayer.LAYER_TILE_BACKGROUND) {
            batch.draw(RenderManager.TILE_TEXTURE, renderX, renderY, tileWidth, tileWidth, this.backgroundTile.u2, this.backgroundTile.v2, this.backgroundTile.u, this.backgroundTile.v);
        }

        if (layer == TileLayer.LAYER_TILE_FOREGROUND) {
            super.renderTile(batch, renderX, renderY, tileWidth, layer);
            LD39.instance.getLightMap().addLight(renderX + tileWidth / 2, renderY + tileWidth / 2, (float) (15 - Math.random() * 2), new Color(0xbb5510FF));
        }
    }
}
