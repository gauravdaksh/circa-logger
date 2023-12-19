package inyt.circa.com.Logging.service.impl;
import inyt.circa.com.Logging.service.CircaLogger;
import inyt.circa.com.Logging.util.LogLevel;
import org.apache.logging.log4j.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CircaLogManager implements CircaLogger {

    private Logger logger;
    //private ExtendedLoggerWrapper log;

    public CircaLogManager() {
    }
    public CircaLogManager(Class<?> clazz) {

        this.logger = LogManager.getLogger(clazz);
        /*log = new ExtendedLoggerWrapper((ExtendedLogger) logger,
                logger.getName(), logger.getMessageFactory());*/
    }

     public static CircaLogger getLogger(Class<?> clazz) {
        return new CircaLogManager(clazz);
    }

    public void log(String logLevel, String message) {

        if(isValid(message) && isValid(logLevel)){
        if(logLevel.equalsIgnoreCase(LogLevel.LOG_LEVEL_INFO)){
            logger.info("Message: {}", message);
        } else if (logLevel.equalsIgnoreCase(LogLevel.LOG_LEVEL_DEBUG)) {
            logger.debug("Message: {}", message);
        } else if (logLevel.equalsIgnoreCase(LogLevel.LOG_LEVEL_WARN)) {
            logger.warn("Message: {}", message);
        } else {
            logger.warn("Log Level is not valid. Log level should be INFO,WARN and DEBUG");
        }
        }else {
            logger.warn("Message and Log level should not be blank");
        }

    }


    public void logWithUUID(String logLevel,String user,String uuid, String message){
        ThreadContext.put("uuid", uuid);
        if(logLevel.equalsIgnoreCase(LogLevel.LOG_LEVEL_INFO)){
            logger.info("UUID: {},User: {}, Message: {}",uuid, user, message);
        } else if (logLevel.equals(LogLevel.LOG_LEVEL_DEBUG)) {
            logger.debug("UUID: {}, Message: {}",uuid, user, message);
        } else if (logLevel.equals(LogLevel.LOG_LEVEL_WARN)) {
            logger.warn("UUID: {}, Message: {}",uuid, user, message);
        }
        ThreadContext.remove("uuid");
    }

    public void logWithCorrelationId(String correlationId, String message) {

        ThreadContext.put("correlationId", correlationId);
        logger.info(" (Correlation ID: " + correlationId + ")"+message);
        ThreadContext.remove("correlationId");
    }

    public void logWithError(String correlationId, String screenName, String message, Throwable throwable){

        ThreadContext.put("CorrelationId", correlationId);
        logger.error("Error Root cause - CorrelationId: {}, Screen Name: {}, Message: {}, Details: {}", correlationId, screenName, message, throwable.getMessage());
        ThreadContext.remove("CorrelationId");

       /* String errorId = UUID.randomUUID().toString();
        return errorId;*/
    }

    public void logHttpRequest(String httpMethod, String path, String body, String correlationId) {
        ThreadContext.put("CorrelationId", correlationId);
        logger.info("HTTP Request - Method: {}, Path: {}, Body: {}", httpMethod, path, body);
        ThreadContext.remove("CorrelationId");
    }


    public void logHttpResponse(int statusCode, String responseBody, String correlationId) {
        ThreadContext.put("CorrelationId", correlationId);
        logger.info("HTTP Response - Status Code: {}, Body: {}", statusCode, responseBody);
        ThreadContext.remove("CorrelationId");
    }

    public void logScreenName(String screenName) {
        logger.info("Circa Screen Name: {}", screenName);

    }

    public void logMethodEntry(String methodName) {

        logger.trace("Entering method: {}", methodName);
    }

    public void logMethodExit(String methodName) {

        logger.trace("Exiting method: {}", methodName);
    }

    public void logDatabaseQuery(String query) {

        logger.debug("Database Query: {}", query);
    }

    public void logDatabaseError(String errorMessage, Throwable throwable) {
        logger.error("Database Error - Message: {}", errorMessage, throwable);
    }

    private boolean isValid(String parameter) {
        return parameter != null && !parameter.isEmpty();
    }

}
