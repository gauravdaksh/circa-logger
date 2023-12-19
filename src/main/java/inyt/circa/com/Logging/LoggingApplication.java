package inyt.circa.com.Logging;


import inyt.circa.com.Logging.service.CircaLogger;
import inyt.circa.com.Logging.service.impl.CircaLogManager;
import inyt.circa.com.Logging.util.LogLevel;
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
