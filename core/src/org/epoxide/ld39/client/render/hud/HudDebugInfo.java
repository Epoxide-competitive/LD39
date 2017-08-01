package org.epoxide.ld39.client.render.hud;

import java.util.ArrayList;
import java.util.List;

import org.epoxide.ld39.LD39;
import org.epoxide.ld39.entity.EntityPlayer;
import org.epoxide.ld39.util.RenderUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class HudDebugInfo implements IHud {

    @Override
    public void renderHud (SpriteBatch batch, float delta) {

        final LD39 game = LD39.instance;
        final BitmapFont font = game.getFont();
        final EntityPlayer player = game.getEntityPlayer();
        final Camera camera = game.getCamera();

        final float textX = camera.position.x - Gdx.graphics.getWidth() / 2 + 10;
        final float textY = camera.position.y + Gdx.graphics.getHeight() / 2;
        
        final List<String> list = new ArrayList<>();
                
        list.add("FPS: " + Gdx.graphics.getFramesPerSecond());
        list.add("GL_RENDERER = " + Gdx.gl.glGetString(GL20.GL_RENDERER));
        list.add("GL_VENDOR = " + Gdx.gl.glGetString(GL20.GL_VENDOR));
        list.add("GL_VERSION = " + Gdx.gl.glGetString(GL20.GL_VERSION));
        list.add("WIDTH = " + Gdx.graphics.getWidth());
        list.add("HEIGHT = " + Gdx.graphics.getHeight());
        list.add("X = " + player.x);
        list.add("Y = " + player.y);
        list.add("GAMESTATE = " + game.getState());
        list.add("STEP = " + game.getStep());
        list.add("ACCUMULATOR = " + game.getAccumulator());
        list.add("DELTA = " + delta);
        list.add("MX = " + Math.round(((Gdx.input.getX()-LD39.instance.getCamera().position.x)/32) + player.x));
        list.add("MY = " + Math.round((-(Gdx.input.getY()-LD39.instance.getCamera().position.y)/32) + player.y));
        list.add("MTILE = " + game.getWorld().getTileState(Math.round(((Gdx.input.getX()-LD39.instance.getCamera().position.x)/32) + player.x), Math.round((-(Gdx.input.getY()-LD39.instance.getCamera().position.y)/32) + player.y)).tile.getIdentifier().toString());
        list.add("Health = " + player.power);
        list.add(game.getWorld().getTileState((int) player.x, (int) player.y + 1).tile.getIdentifier().toString());
        
        batch.begin();
        batch.setShader(game.getDefaultShader());
        RenderUtils.renderMultilineString(batch, font, list, textX, textY, 20);
        batch.end();
    }
}
