package org.epoxide.ld39.tile;

import org.epoxide.ld39.client.render.RenderManager;
import org.epoxide.ld39.client.render.TileLayer;
import org.epoxide.ld39.world.Direction;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TileDirectionState extends TileState {
    public Direction direction;

    public TileDirectionState (Tile tile, float x, float y) {
        super(tile, x, y);
        this.direction = Direction.NORTH;
    }

    @Override
    public void renderTile (SpriteBatch batch, float renderX, float renderY, float tileWidth, TileLayer layer) {

        batch.draw(RenderManager.TILE_TEXTURE, renderX, renderY, tileWidth, tileWidth, this.tile.u2, this.tile.v2, this.tile.u, this.tile.v);
    }

    public Vector2 rotateUV (float angle, float u, float v) {

        final double cos_theta = Math.cos(angle);
        final double sin_theta = Math.sin(angle);

        final float x = u - 16f / 256f;
        final float y = v - 16f / 256f;
        return new Vector2((float) (x * cos_theta - y * sin_theta + 16f / 256f), (float) (x * sin_theta + y * cos_theta + 16f / 256f));
    }
}
