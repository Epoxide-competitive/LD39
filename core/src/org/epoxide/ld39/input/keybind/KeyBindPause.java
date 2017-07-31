package org.epoxide.ld39.input.keybind;

import org.epoxide.ld39.GameState;
import org.epoxide.ld39.LD39;

public class KeyBindPause extends KeyBind {

    public KeyBindPause (int keycode) {

        super(keycode);
    }

    @Override
    public void onPressed () {

        final GameState currentState = LD39.instance.getState();
        LD39.instance.setState(currentState == GameState.RUNNING ? GameState.PAUSED : currentState == GameState.PAUSED ? GameState.RUNNING : GameState.RUNNING);
    }
}