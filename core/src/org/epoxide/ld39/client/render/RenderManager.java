package org.epoxide.ld39.client.render;

import com.badlogic.gdx.math.Vector2;
import org.epoxide.ld39.GameObject;
import org.epoxide.ld39.LD39;
import org.epoxide.ld39.client.render.hud.HudDebugInfo;
import org.epoxide.ld39.client.render.hud.HudHealth;
import org.epoxide.ld39.client.render.hud.IHud;
import org.epoxide.ld39.entity.*;
import org.epoxide.ld39.tile.Tile;
import org.epoxide.ld39.tile.TileState;
import org.epoxide.ld39.world.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderManager {

    public static final Texture TILE_TEXTURE = new Texture("assets/ld39/textures/tile/tiles.png");
    public static final Texture ITEM_TEXTURE = new Texture("assets/ld39/textures/items/items.png");
    public static final Texture ENTITY_TEXTURE = new Texture("assets/ld39/textures/entities/entities.png");
    
    
    // TODO move huds to a registry. Probably not in this class.
    private final IHud debugHud = new HudDebugInfo();
    private final IHud healthHud = new HudHealth();

    private SpriteBatch batch;

    public RenderManager()
    {
        batch = new SpriteBatch();
    }

    public SpriteBatch getBatch()
    {
        return batch;
    }
    public void renderGame (SpriteBatch batch, float delta) {
        World world = LD39.instance.getWorld();
        if(world != null)
            world.renderWorld(batch, delta);
        this.renderPlayer(batch, delta);
        this.renderHud(batch, delta);
    }
    public static Vector2 posRelativeToCamera(Vector2 pos)
    {
        final float renderX = pos.x * LD39.tileWidth - (LD39.instance.getEntityPlayer().x * LD39.tileWidth - Gdx.graphics.getWidth() / 2 + LD39.tileWidth / 2);
        final float renderY = pos.y * LD39.tileWidth - (LD39.instance.getEntityPlayer().y * LD39.tileWidth - Gdx.graphics.getHeight() / 2 + LD39.tileWidth / 2);
        return new Vector2(renderX,renderY);
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
        float intensity = ((LD39.instance.getEntityPlayer().power/LD39.instance.getEntityPlayer().maxPower) + 0.02f) * (float)(25- (Math.random()>0.1 ? Math.random() * 4 : 0));
        LD39.instance.getLightMap().addLight(Gdx.graphics.getWidth() / 2 - LD39.tileWidth / 2 + 16, Gdx.graphics.getHeight() / 2 - LD39.tileWidth / 2 + 16, intensity, Color.WHITE);
        batch.end();
    }

    private void renderEntities (SpriteBatch batch, float delta) {
        
    }

    private void renderItems (SpriteBatch batch, float delta) {

    }
    public int getScreenLeftEdge()
    {
        return (int)Math.floor(LD39.instance.getEntityPlayer().x - Gdx.graphics.getWidth()/2);
    }
    public int getScreenRightEdge()
    {
        return (int)Math.ceil(LD39.instance.getEntityPlayer().x + Gdx.graphics.getWidth()/2);
    }
    public int getScreenBottomEdge()
    {
        return (int)Math.floor(LD39.instance.getEntityPlayer().y - Gdx.graphics.getHeight()/2);
    }
    public int getScreenTopEdge()
    {
        return (int)Math.ceil(LD39.instance.getEntityPlayer().y + Gdx.graphics.getHeight()/2);
    }
}
