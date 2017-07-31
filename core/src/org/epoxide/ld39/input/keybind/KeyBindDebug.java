package org.epoxide.ld39.input.keybind;

import org.epoxide.ld39.LD39;

public class KeyBindDebug extends KeyBind {

    public KeyBindDebug (int keycode) {

        super(keycode);
    }

    @Override
    public void onPressed () {

        LD39.instance.toggleDebug();
    }
}