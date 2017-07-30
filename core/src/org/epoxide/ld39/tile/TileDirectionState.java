package org.epoxide.ld39.tile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import org.epoxide.ld39.client.render.RenderManager;
import org.epoxide.ld39.client.render.TileLayer;
import org.epoxide.ld39.world.Direction;

public class TileDirectionState extends TileState {
    public Direction direction;

    public TileDirectionState(Tile tile, float x, float y) {
        super(tile, x, y);
        this.direction = Direction.NORTH;
    }

    @Override
    public void renderTile(SpriteBatch batch, float renderX, float renderY, float tileWidth, TileLayer layer) {
        batch.draw(RenderManager.TILE_TEXTURE, renderX, renderY, tileWidth, tileWidth, this.tile.u2, tile.v2, tile.u, tile.v);
    }

    public Vector2 rotateUV(float angle, float u, float v) {
        double cos_theta = Math.cos(angle);
        double sin_theta = Math.sin(angle);

        float x = (u - 16f / 256f);
        float y = (v - 16f / 256f);
        return new Vector2((float) (x * cos_theta - y * sin_theta + (16f / 256f)), (float) (x * sin_theta + y * cos_theta + (16f / 256f)));
    }
}
