package org.epoxide.ld39.client.render.hud;

import org.epoxide.ld39.LD39;
import org.epoxide.ld39.entity.EntityPlayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HudHealth implements IHud {

    public static final Texture HUD_BATTERY_TEXTURE = new Texture("assets/ld39/textures/hud/battery.png");

    @Override
    public void renderHud (SpriteBatch batch, float delta) {

        final EntityPlayer player = LD39.instance.getEntityPlayer();

        batch.begin();
        batch.setShader(LD39.instance.getDefaultShader());
        batch.draw(HUD_BATTERY_TEXTURE, Gdx.graphics.getWidth() - 29, Gdx.graphics.getHeight() - 30, 16, player.power / player.maxPower * 24, 0.8f, 1, 0, 0.6f);
        batch.draw(HUD_BATTERY_TEXTURE, Gdx.graphics.getWidth() - 32, Gdx.graphics.getHeight() - 32, 20, 30, 1, 0.55f, 0, 0);
        batch.end();
    }
}