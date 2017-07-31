package org.epoxide.ld39.input.keybind;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Gdx;

public class KeyBindMulti extends KeyBind {

    private Set<Integer> keys = new HashSet<>();
    
    public KeyBindMulti (int... keycodes) {
        
        super(-1);
        
        for (int key : keycodes) {
            
            keys.add(key);
        }
    }
    
    @Override
    public boolean isPressed () {
        
        for (Integer key : this.keys) {
            
            if ( Gdx.input.isKeyPressed(key)) {
                
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public boolean isListeningForCode(int code) {
        
        return this.keys.contains(code);
    }
}
