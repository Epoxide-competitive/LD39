package org.epoxide.ld39.input.keybind;

import org.epoxide.commons.registry.Registerable;

import com.badlogic.gdx.Gdx;

public class KeyBind extends Registerable<KeyBind> {

    private int keycode;

    public KeyBind (int keycode) {

        this.keycode = keycode;
    }

    public boolean isPressed () {

        return Gdx.input.isKeyPressed(this.getKeyCode());
    }

    public void onPressed () {

    }

    public void onReleased () {

    }

    public void onUpdate (float delta) {

    }

    public int getKeyCode () {

        return this.keycode;
    }

    public void setKeyCode (int key) {

        this.keycode = key;
    }

    public boolean isListeningForCode (int code) {

        return this.keycode == code;
    }
}