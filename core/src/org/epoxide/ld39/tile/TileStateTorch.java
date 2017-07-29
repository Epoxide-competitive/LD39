package org.epoxide.ld39.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.epoxide.ld39.LD39;
import org.epoxide.ld39.world.Direction;

public class TileStateTorch extends TileDirectionState {
    public TileStateTorch(float x, float y) {
        super(Tile.TORCH, x, y);
        this.direction = Direction.NORTH;
    }

    @Override
    public void renderTile(SpriteBatch batch, float renderX, float renderY, float tileWidth) {
        super.renderTile(batch, renderX, renderY, tileWidth);
        LD39.instance.lightMap.addLight(renderX + tileWidth / 2, renderY + tileWidth / 2, 15, new Color(0xbb5510FF));
    }
}
