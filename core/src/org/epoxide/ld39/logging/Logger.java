package org.epoxide.ld39.logging;

import com.badlogic.gdx.Gdx;

public class Logger {
    
    private String name;
    
    public Logger(String name) {
        
        this.name = name;
    }
    
    public String getName() {
        
        return this.name;
    }
    
    public void log(String message, Object... params) {
        
        Gdx.app.log(this.getName(), String.format(message, params));
    }
    
    public void debug(String message, Object... params) {
        
        Gdx.app.debug(this.getName(), String.format(message, params));
    }
    
    public void error(String message, Object... params) {
        
        Gdx.app.error(this.getName(), String.format(message, params));
    }
}