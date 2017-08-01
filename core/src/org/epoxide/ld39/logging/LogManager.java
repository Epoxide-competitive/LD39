package org.epoxide.ld39.logging;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;

public class LogManager {
    
    private static final List<Logger> loggers = new ArrayList<>();
    private static final List<ILogFilter> filters = new ArrayList<>();
    
    protected static void log(Level level, Logger log, String message, Object... params) {
        
        final String formatted = String.format(message, params);
        
        if (!isMessageAllowed(log, formatted)) {
            
            return;
        }
        
        switch (level) {
            
            case DEBUG:
                Gdx.app.debug(log.getName(), formatted);
                break;
                
            case ERROR:
                Gdx.app.error(log.getName(), formatted);
                break;
                
            case INFO:
                Gdx.app.log(log.getName(), formatted);
                break;
                
            default:
                break;
        }
    }
    
    protected static void log(Level level, Logger log, Throwable throwable, String message, Object... params) {
        
        final String formatted = String.format(message, params);
        
        if (!isMessageAllowed(log, formatted)) {
            
            return;
        }
        
        switch (level) {
            
            case DEBUG:
                Gdx.app.debug(log.getName(), formatted, throwable);
                break;
                
            case ERROR:
                Gdx.app.error(log.getName(), formatted, throwable);
                break;
                
            case INFO:
                Gdx.app.log(log.getName(), formatted, throwable);
                break;
                
            default:
                break;
        }
    }
    
    public static Logger createLogger(String name) {
        
        final Logger logger = new Logger(name);        
        loggers.add(logger);
        return logger;
    }
    
    public static void addFilter(ILogFilter filter) {
        
        filters.add(filter);
    }
    
    public static void removeFilter(ILogFilter filter) {
        
        filters.remove(filter);
    }
    
    private static boolean isMessageAllowed(Logger log, String message) {
        
        for (ILogFilter filter : filters) {
            
            if (!filter.allowMessage(log, message)) {

                return false;
            }
        }       
        
        return true;
    }
}
