package main.java.org.urbanladder.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerManager {
    private LoggerManager() {}

    public static Logger getLogger(Class<?> logger) {
        return LogManager.getLogger(logger);
    }
}