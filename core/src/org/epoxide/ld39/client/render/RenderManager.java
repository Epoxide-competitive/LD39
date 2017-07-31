package org.epoxide.ld39.client.render;

import org.epoxide.ld39.LD39;
import org.epoxide.ld39.client.render.hud.HudDebugInfo;
import org.epoxide.ld39.client.render.hud.HudHealth;
import org.epoxide.ld39.client.render.hud.IHud;
import org.epoxide.ld39.entity.EntityPlayer;
import org.epoxide.ld39.tile.Tile;
import org.epoxide.ld39.tile.TileState;
import org.epoxide.ld39.world.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderManager {

    public static final Texture TILE_TEXTURE = new Texture("assets/ld39/textures/tile/tiles.png");
    public static final Texture ENTITY_TEXTURE = new Texture("assets/ld39/textures/entities/entities.png");
    
    
    // TODO move huds to a registry. Probably not in this class.
    private final IHud debugHud = new HudDebugInfo();
    private final IHud healthHud = new HudHealth();

    public void renderGame (SpriteBatch batch, float delta) {

        this.renderTiles(batch, delta, TileLayer.LAYER_TILE_BACKGROUND);
        this.renderEntities(batch, delta);
        this.renderTiles(batch, delta, TileLayer.LAYER_TILE_FOREGROUND);
        this.renderPlayer(batch, delta);
        this.renderHud(batch, delta);

    }

    private void renderHud (SpriteBatch batch, float delta) {

        if (LD39.instance.isDebugEnabled()) {

            this.debugHud.renderHud(batch, delta);
        }

        this.healthHud.renderHud(batch, delta);
    }

    private void renderPlayer (SpriteBatch batch, float delta) {

        batch.begin();
        batch.draw(ENTITY_TEXTURE, Gdx.graphics.getWidth() / 2 - LD39.tileWidth / 2, Gdx.graphics.getHeight() / 2 - LD39.tileWidth / 2, LD39.tileWidth, LD39.tileWidth, 1*0.0625f, 1*0.0625f, 0, 0);
        LD39.instance.getLightMap().addLight(Gdx.graphics.getWidth() / 2 - LD39.tileWidth / 2 + 16, Gdx.graphics.getHeight() / 2 - LD39.tileWidth / 2 + 16, 25, Color.WHITE);
        batch.end();
    }

    private void renderEntities (SpriteBatch batch, float delta) {
        
    }

    private void renderTiles (SpriteBatch batch, float delta, TileLayer layer) {

        final EntityPlayer entityPlayer = LD39.instance.getEntityPlayer();
        final World world = entityPlayer.world;

        final int x = (int) (entityPlayer.x * LD39.tileWidth - Gdx.graphics.getWidth() / 2 + LD39.tileWidth / 2);
        final int y = (int) (entityPlayer.y * LD39.tileWidth - Gdx.graphics.getHeight() / 2 + LD39.tileWidth / 2);

        batch.begin();
        for (int i = 0; i < world.getMapWidth(); i++) {
            for (int j = 0; j < world.getMapHeight(); j++) {
                final TileState tileState = world.getTileState(i, j);
                if (tileState.getTile() != Tile.VOID) {
                    final float renderX = i * LD39.tileWidth - x;
                    final float renderY = j * LD39.tileWidth - y;
                    if (renderX >= -LD39.tileWidth && renderX <= Gdx.graphics.getWidth() && renderY >= -LD39.tileWidth && renderY <= Gdx.graphics.getHeight()) {
                        if (tileState.shouldRenderLayer(layer)) {
                            tileState.renderTile(batch, renderX, renderY, LD39.tileWidth, layer);
                            // Tile tile = tileState.getTile();
                        }
                    }
                }
            }
        }
        batch.end();
    }

}
