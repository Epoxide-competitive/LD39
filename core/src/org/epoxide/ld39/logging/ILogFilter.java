package org.epoxide.ld39.logging;

public interface ILogFilter {
    
    boolean allowMessage(Logger log, String message);
}