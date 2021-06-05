package com.marcguillem.tradfriguicore.Services.ConsoleLogTool;

public interface IConsoleLogToolService {

    void logInfo(Class clazz, String message);
    void logError(Class clazz, String message);
    void logWarning(Class clazz, String message);

}
