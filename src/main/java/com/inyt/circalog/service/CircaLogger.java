package com.inyt.circalog.service;


import org.springframework.stereotype.Service;

@Service
public interface CircaLogger {

    /**
     * Log.
     *
     * @param logLevel the log level
     * @param message  the message
     */
    public void log(String logLevel, String message);

    public void logScreenName(String screenName);

    public void logMethodEntry(String methodName);

    public void logMethodExit(String methodName);

    public void logDatabaseQuery(String query);

    public void logDatabaseError(String errorMessage, Throwable throwable);

    public void logWithUUID(String logLevel,String user,String uuid, String message);

    public void logWithError(String correlationId, String screenName, String message, Throwable throwable);

    public void logWithCorrelationId(String correlationId, String message);

    public void logHttpRequest(String method, String path, String body, String correlationId);

    public void logHttpResponse(int statusCode, String responseBody, String correlationId);



}
