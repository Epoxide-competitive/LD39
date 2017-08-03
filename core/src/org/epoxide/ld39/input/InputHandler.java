package org.epoxide.ld39.input;

import com.badlogic.gdx.*;
import org.epoxide.commons.registry.Identifier;
import org.epoxide.commons.registry.NamedRegistry;
import org.epoxide.ld39.LD39;
import org.epoxide.ld39.entity.EntityPlayer;
import org.epoxide.ld39.input.keybind.KeyBind;
import org.epoxide.ld39.input.keybind.KeyBindDebug;
import org.epoxide.ld39.input.keybind.KeyBindPause;
import org.epoxide.ld39.input.keybind.KeyBindMovement;
import org.epoxide.ld39.tile.*;
import org.epoxide.ld39.util.Direction;

import com.badlogic.gdx.Input.Keys;
import org.epoxide.ld39.world.World;

public class InputHandler implements InputProcessor {

    // TODO Centralize registries
    public static final NamedRegistry<KeyBind> REGISTRY = new NamedRegistry<>();

    public static final KeyBind UP = registerKeyBind("up", new KeyBindMovement(Direction.UP, Keys.W, Keys.UP));
    public static final KeyBind DOWN = registerKeyBind("down", new KeyBindMovement(Direction.DOWN, Keys.S, Keys.DOWN));
    public static final KeyBind LEFT = registerKeyBind("left", new KeyBindMovement(Direction.LEFT, Keys.A, Keys.LEFT));
    public static final KeyBind RIGHT = registerKeyBind("right", new KeyBindMovement(Direction.RIGHT, Keys.D, Keys.RIGHT));
    
    public static final KeyBind POWER = registerKeyBind("power", new KeyBind(Keys.F){
        @Override
        public void onPressed() {
            super.onPressed();
            LD39.instance.getEntityPlayer().power =  LD39.instance.getEntityPlayer().maxPower;
        }
    });
    
    public static final KeyBind DEBUG = registerKeyBind("debug", new KeyBindDebug(Keys.TAB));
    public static final KeyBind PAUSE = registerKeyBind("pause", new KeyBindPause(Keys.E));

    public static KeyBind registerKeyBind (String id, KeyBind bind) {

        bind.setIdentifier(new Identifier(LD39.ID, id));
        REGISTRY.registerValue(bind);
        return bind;
    }

    public void onUpdate (float delta) {

        for (final KeyBind key : REGISTRY) {

            if (key.isPressed()) {

                key.onUpdate(delta);
            }
        }
    }

    @Override
    public boolean keyDown (int keycode) {

        for (final KeyBind key : REGISTRY) {

            if (key.isListeningForCode(keycode)) {

                key.onPressed();
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean keyUp (int keycode) {

        for (final KeyBind key : REGISTRY) {

            if (key.isListeningForCode(keycode)) {

                key.onReleased();
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean keyTyped (char character) {

        return false;
    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        EntityPlayer player = LD39.instance.getEntityPlayer();
        World world = player.world;
        int x = Math.round(((Gdx.input.getX()-LD39.instance.getCamera().position.x)/32) + player.x);
        int y = Math.round((-(Gdx.input.getY()-LD39.instance.getCamera().position.y)/32) + player.y);
        return true;
    }

    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged (int screenX, int screenY, int pointer) {

        return false;
    }

    @Override
    public boolean mouseMoved (int screenX, int screenY) {

        return false;
    }

    @Override
    public boolean scrolled (int amount) {

        return false;
    }
}