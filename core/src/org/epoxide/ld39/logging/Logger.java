package org.epoxide.ld39.logging;

public class Logger {
    
    private String name;
    
    protected Logger(String name) {
        
        this.name = name;
    }
    
    public String getName() {
        
        return this.name;
    }
    
    public void log(String message, Object... params) {
        
        LogManager.log(Level.INFO, this, message, params);
    }
    
    public void debug(String message, Object... params) {
        
        LogManager.log(Level.DEBUG, this, message, params);
    }
    
    public void error(String message, Object... params) {
        
        LogManager.log(Level.ERROR, this, message, params);
    }
    
    public void log(Throwable throwable, String message, Object... params) {
        
        LogManager.log(Level.INFO, this, throwable, message, params);
    }
    
    public void debug(Throwable throwable, String message, Object... params) {
        
        LogManager.log(Level.DEBUG, this, throwable, message, params);
    }
    
    public void error(Throwable throwable, String message, Object... params) {
        
        LogManager.log(Level.ERROR, this, throwable, message, params);
    }
}