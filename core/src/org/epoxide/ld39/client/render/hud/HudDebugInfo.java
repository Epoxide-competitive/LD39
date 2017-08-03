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
        list.add("MX = " + game.getMouseWorldX());
        list.add("MY = " + game.getMouseWorldY());
        list.add("Health = " + player.power);
        Runtime runtime = Runtime.getRuntime();

        long totalMB = runtime.totalMemory() / 1024L / 1024L;
        long freeMB = runtime.freeMemory() / 1024L / 1024L;
        list.add("Memory: "+ (totalMB - freeMB) + "mb/"+ totalMB+"mb");
        //TODO convert to the new framework correctly
        //list.add("MTILE = " + game.getWorld().getGameObjectsAt(game.getMouseWorldX(), game.getMouseWorldY())..getIdentifier().toString());
        //list.add(game.getWorld().getGameObjectsAt((int) player.x, (int) player.y + 1).get(0).name.toString());
        
        batch.begin();
        batch.setShader(game.getDefaultShader());
        RenderUtils.renderMultilineString(batch, font, list, textX, textY, 20);
        batch.end();
    }
}
