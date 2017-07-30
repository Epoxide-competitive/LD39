package org.epoxide.ld39.client.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.epoxide.ld39.LD39;
import org.epoxide.ld39.entity.EntityPlayer;
import org.epoxide.ld39.tile.Tile;
import org.epoxide.ld39.tile.TileState;
import org.epoxide.ld39.world.World;

public class RenderManager {
    public static final Texture TILE_TEXTURE = new Texture("assets/ld39/textures/tile/tiles.png");

    public void renderGame(SpriteBatch batch, float delta) {
        this.renderTiles(batch, delta, TileLayer.LAYER_TILE_BACKGROUND);
        this.renderEntities(batch, delta);
        this.renderTiles(batch, delta, TileLayer.LAYER_TILE_FOREGROUND);
        this.renderPlayer(batch, delta);
        
    }

    private void renderPlayer(SpriteBatch batch, float delta) {
        batch.begin();
        batch.draw(TILE_TEXTURE, Gdx.graphics.getWidth() / 2 - LD39.tileWidth / 2, Gdx.graphics.getHeight() / 2 - LD39.tileWidth / 2, LD39.tileWidth, LD39.tileWidth, 0, 0, 1, 1);
        batch.end();
    }

    private void renderEntities(SpriteBatch batch, float delta) {
    }

    private void renderTiles(SpriteBatch batch, float delta, TileLayer layer) {
        final EntityPlayer entityPlayer = LD39.instance.entityPlayer;
        final World world = entityPlayer.world;

        int x = (int) (entityPlayer.x * LD39.tileWidth - Gdx.graphics.getWidth() / 2 + LD39.tileWidth / 2);
        int y = (int) (entityPlayer.y * LD39.tileWidth - Gdx.graphics.getHeight() / 2 + LD39.tileWidth / 2);

        batch.begin();
        for (int i = 0; i < world.getMapWidth(); i++) {
            for (int j = 0; j < world.getMapHeight(); j++) {
                TileState tileState = world.getTileState(i, j);
                if (tileState.getTile() != Tile.VOID) {
                    float renderX = (i * LD39.tileWidth) - x;
                    float renderY = (j * LD39.tileWidth) - y;
                    if (renderX >= -LD39.tileWidth && renderX <= Gdx.graphics.getWidth() && renderY >= -LD39.tileWidth && renderY <= Gdx.graphics.getHeight())
                        if (tileState.shouldRenderLayer(layer)) {
                            tileState.renderTile(batch, renderX, renderY, LD39.tileWidth, layer);
//                            Tile tile = tileState.getTile();
                        }
                }
            }
        }
        batch.end();
    }


}
