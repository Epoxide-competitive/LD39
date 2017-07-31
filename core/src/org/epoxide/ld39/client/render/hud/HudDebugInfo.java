package org.epoxide.ld39.client.render.hud;

import org.epoxide.ld39.LD39;
import org.epoxide.ld39.entity.EntityPlayer;

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

        batch.begin();
        batch.setShader(game.getDefaultShader());
        final float textX = camera.position.x - Gdx.graphics.getWidth() / 2 + 10;
        final float textY = camera.position.y + Gdx.graphics.getHeight() / 2;
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), textX, textY - 10);
        font.draw(batch, "GL_RENDERER = " + Gdx.gl.glGetString(GL20.GL_RENDERER), textX, textY - 30);
        font.draw(batch, "GL_VENDOR = " + Gdx.gl.glGetString(GL20.GL_VENDOR), textX, textY - 50);
        font.draw(batch, "GL_VERSION = " + Gdx.gl.glGetString(GL20.GL_VERSION), textX, textY - 70);
        font.draw(batch, "WIDTH = " + Gdx.graphics.getWidth(), textX, textY - 90);
        font.draw(batch, "HEIGHT = " + Gdx.graphics.getHeight(), textX, textY - 110);
        font.draw(batch, "X = " + player.x, textX, textY - 130);
        font.draw(batch, "Y = " + player.y, textX, textY - 150);
        font.draw(batch, "GAMESTATE = " + game.getState(), textX, textY - 170);
        font.draw(batch, "STEP = " + game.getStep(), textX, textY - 190);
        font.draw(batch, "ACCUMULATOR = " + game.getAccumulator(), textX, textY - 210);
        font.draw(batch, "DELTA = " + delta, textX, textY - 230);
        font.draw(batch, "MX = " + Math.round(((Gdx.input.getX()-LD39.instance.getCamera().position.x)/32) + player.x), textX, textY - 250);
        font.draw(batch, "MY = " + Math.round((-(Gdx.input.getY()-LD39.instance.getCamera().position.y)/32) + player.y), textX, textY - 270);
        font.draw(batch, "MTILE = " + game.getWorld().getTileState(Math.round(((Gdx.input.getX()-LD39.instance.getCamera().position.x)/32) + player.x), Math.round((-(Gdx.input.getY()-LD39.instance.getCamera().position.y)/32) + player.y)).tile.getIdentifier().toString(), textX, textY - 290);
    
        font.draw(batch, game.getWorld().getTileState((int) player.x, (int) player.y + 1).tile.getIdentifier().toString(), textX, textY - 310);
        batch.end();
    }
}
