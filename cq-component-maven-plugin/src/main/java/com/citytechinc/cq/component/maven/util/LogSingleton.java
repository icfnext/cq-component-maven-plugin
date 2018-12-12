package com.citytechinc.cq.component.maven.util;

import org.apache.maven.plugin.logging.Log;

public class LogSingleton {

    private static LogSingleton instance;

    private Log logger;

    private LogSingleton() {
    }

    public static final LogSingleton getInstance() {
        if (instance == null) {
            instance = new LogSingleton();
        }

        return instance;
    }

    public void setLogger(Log logger) {
        this.logger = logger;
    }

    public void debug(CharSequence content) {
        if (logger != null) {
            logger.debug(content);
        }
    }

    public void debug(CharSequence content, Throwable error) {
        if (logger != null) {
            logger.debug(content, error);
        }
    }

    public void debug(Throwable error) {
        if (logger != null) {
            logger.debug(error);
        }
    }

    public void error(CharSequence content) {
        if (logger != null) {
            logger.error(content);
        }
    }

    public void error(CharSequence content, Throwable error) {
        if (logger != null) {
            logger.error(content, error);
        }
    }

    public void error(Throwable error) {
        if (logger != null) {
            logger.error(error);
        }
    }

    public void info(CharSequence content) {
        if (logger != null) {
            logger.info(content);
        }
    }

    public void info(CharSequence content, Throwable error) {
        if (logger != null) {
            logger.info(content, error);
        }
    }

    public void info(Throwable error) {
        if (logger != null) {
            logger.info(error);
        }
    }

    public void warn(CharSequence content) {
        if (logger != null) {
            logger.warn(content);
        }
    }

    public void warn(CharSequence content, Throwable error) {
        if (logger != null) {
            logger.warn(content, error);
        }
    }

    public void warn(Throwable error) {
        if (logger != null) {
            logger.warn(error);
        }
    }
}
