package org.epoxide.ld39.tile;

import com.badlogic.gdx.math.Vector2;
import org.epoxide.ld39.GameObject;
import org.epoxide.ld39.client.render.IRenderable;
import org.epoxide.ld39.client.render.RenderManager;
import org.epoxide.ld39.client.render.TileLayer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static org.epoxide.ld39.LD39.tileWidth;

public class TileState extends GameObject implements IRenderable {

    public final Tile tile;
    private final float x, y;

    public TileState (Tile tile, float x, float y) {
        this.tile = tile;
        this.x = x;
        this.y = y;
    }

    public Tile getTile () {

        return this.tile;
    }

    public boolean shouldRenderLayer (TileLayer layer) {

        return this.tile.shouldRenderLayer(layer);
    }

    public void renderTile (SpriteBatch batch, float renderX, float renderY, float tileWidth, TileLayer layer) {

    }

    @Override
    public void render(SpriteBatch batch, Vector2 screenPos, float delta) {
        batch.draw(RenderManager.TILE_TEXTURE, screenPos.x,screenPos.y, tileWidth, tileWidth, this.tile.u2, this.tile.v2, this.tile.u, this.tile.v);
    }

    @Override
    public int renderLayer() {
        return 0;
    }

    @Override
    public int renderPriority() {
        return 0;
    }

    @Override
    public boolean isOpaque() {
        return tile.isSolid();//TODO implement tile is opaque
    }
}
