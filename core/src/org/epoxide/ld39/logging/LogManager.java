package org.epoxide.ld39.logging;

import java.util.ArrayList;
import java.util.List;

public class LogManager {
    
    private static final List<ILogFilter> filters = new ArrayList();
    
    public static Logger createLogger(String name) {
        
        return null;
    }
    
    public static void addFilter(ILogFilter filter) {
        
        filters.add(filter);
    }
    
    public static void removeFilter(ILogFilter filter) {
        
        filters.remove(filter);
    }
}
