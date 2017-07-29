package org.epoxide.ld39.tile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import org.epoxide.ld39.client.render.RenderManager;
import org.epoxide.ld39.world.Direction;

public class TileDirectionState extends TileState {
    public Direction direction;

    public TileDirectionState(Tile tile, float x, float y) {
        super(tile, x, y);
        this.direction = Direction.NORTH;
    }

    public void renderTile(SpriteBatch batch, float renderX, float renderY, float tileWidth) {
        if (direction == Direction.NORTH)
            batch.draw(RenderManager.TILE_TEXTURE, renderX, renderY, tileWidth, tileWidth, this.tile.u2, tile.v2, tile.u, tile.v);
        if (direction == Direction.SOUTH)//EAST
        {
            Vector2 uvMin = rotateUV(90, this.tile.u, this.tile.v);
            Vector2 uvMax = rotateUV(90, this.tile.u2, this.tile.v2);

            batch.draw(RenderManager.TILE_TEXTURE, renderX, renderY, tileWidth, tileWidth, uvMin.x, uvMin.y, uvMax.x, uvMax.y);

        } else if (direction == Direction.SOUTH)
            batch.draw(RenderManager.TILE_TEXTURE, renderX, renderY, tileWidth, tileWidth, this.tile.u2, tile.v, tile.u, tile.v2);
        else if (direction == Direction.SOUTH)
            batch.draw(RenderManager.TILE_TEXTURE, renderX, renderY, tileWidth, tileWidth, this.tile.u2, tile.v, tile.u, tile.v2);
    }

    public Vector2 rotateUV(float angle, float u, float v) {
        double cos_theta = Math.cos(angle);
        double sin_theta = Math.sin(angle);

        float x = (u - 16f / 256f);
        float y = (v - 16f / 256f);
        return new Vector2((float) (x * cos_theta - y * sin_theta + (16f / 256f)), (float) (x * sin_theta + y * cos_theta + (16f / 256f)));
    }
}
