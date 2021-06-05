package com.marcguillem.tradfriguicore.Services.ConsoleLogTool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ConsoleLogToolService implements IConsoleLogToolService{

    public void logInfo(Class clazz, String message) {
        Logger log;
        try {
            log = LoggerFactory.getLogger(clazz);
            log.info(message);
        } finally {
            log = null;
        }
    }

    public void logError(Class clazz, String message) {
        Logger log;
        try {
            log = LoggerFactory.getLogger(clazz);
            log.error(message);
        } finally {
            log = null;
        }
    }

    public void logWarning(Class clazz, String message) {
        Logger log;
        try {
            log = LoggerFactory.getLogger(clazz);
            log.warn(message);
        } finally {
            log = null;
        }
    }

}
