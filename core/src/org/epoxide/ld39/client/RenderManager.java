package org.epoxide.ld39.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.epoxide.ld39.LD39;
import org.epoxide.ld39.entity.EntityPlayer;
import org.epoxide.ld39.tile.Tile;
import org.epoxide.ld39.world.World;

public class RenderManager {
    private final Texture TILE_TEXTURE;

    public RenderManager() {
        this.TILE_TEXTURE = new Texture("assets/ld39/textures/tile/tiles.png");
    }

    public void renderGame(SpriteBatch batch, float delta) {
        for (TileLayer tileLayer : TileLayer.values()) {
            if (tileLayer == TileLayer.LAYER_ENTITY)
                this.renderEntities(batch, delta);
            else
                this.renderTiles(batch, delta, tileLayer);
        }
    }

    private void renderEntities(SpriteBatch batch, float delta) {
    }

    private void renderTiles(SpriteBatch batch, float delta, TileLayer layer) {
        final EntityPlayer entityPlayer = LD39.instance.entityPlayer;
        final World world = entityPlayer.world;

        int x = (int) (entityPlayer.x * LD39.tileWidth - Gdx.graphics.getWidth() / 2);
        int y = (int) (entityPlayer.y * LD39.tileWidth - Gdx.graphics.getHeight() / 2);
        if (entityPlayer.x * LD39.tileWidth < Gdx.graphics.getWidth() / 2) {
            x = 0;
        }
        if (entityPlayer.y * LD39.tileWidth < Gdx.graphics.getHeight() / 2) {
            y = 0;
        }
        if (entityPlayer.x * LD39.tileWidth > world.getMapWidth() * LD39.tileWidth - Gdx.graphics.getWidth() / 2) {
            x = (int) (world.getMapWidth() * LD39.tileWidth - Gdx.graphics.getWidth());
        }
        if (entityPlayer.y * LD39.tileWidth > world.getMapHeight() * LD39.tileWidth - Gdx.graphics.getHeight() / 2) {
            y = (int) (world.getMapHeight() * LD39.tileWidth - Gdx.graphics.getHeight());
        }

        batch.begin();
        batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        if (layer == TileLayer.LAYER_BACKGROUND) {
            batch.setColor(0.5f, 0.5f, 0.5f, 1.0f);
        }
        for (int i = 0; i < world.getTileMap().length; i++) {
            for (int j = 0; j < world.getTileMap()[i].length; j++) {
                Tile t = world.getTileMap()[i][j];
                if (t != Tile.VOID) {
                    batch.draw(TILE_TEXTURE, (i * LD39.tileWidth) - x, (j * LD39.tileWidth) - y, LD39.tileWidth, LD39.tileWidth, t.u2, t.v2, t.u, t.v);
                }
            }
        }
        batch.end();
    }

    private enum TileLayer {
        LAYER_BACKGROUND,
        LAYER_FOREGROUND,
        LAYER_ENTITY,
        LAYER_PARTICLES,
        LAYER_GUI
    }
}
