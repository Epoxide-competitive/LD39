package org.epoxide.ld39.input.keybind;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Gdx;

public class KeyBindMulti extends KeyBind {

    private final Set<Integer> keys = new HashSet<>();

    public KeyBindMulti (int... keycodes) {

        super(-1);

        for (final int key : keycodes) {

            this.keys.add(key);
        }
    }

    @Override
    public boolean isPressed () {

        for (final Integer key : this.keys) {

            if (Gdx.input.isKeyPressed(key)) {

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isListeningForCode (int code) {

        return this.keys.contains(code);
    }
}
