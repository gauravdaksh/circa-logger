package com.inyt.circalog;


import com.inyt.circalog.service.CircaLogger;
import com.inyt.circalog.service.impl.CircaLogManager;
import com.inyt.circalog.util.LogLevel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggingApplication{

	public static void main(String[] args)  {
		SpringApplication.run(LoggingApplication.class, args);

			CircaLogger circaLogger = CircaLogManager.getLogger(LoggingApplication.class);
        circaLogger.log(LogLevel.LOG_LEVEL_INFO,"Logging Framework12345");

	}




}
