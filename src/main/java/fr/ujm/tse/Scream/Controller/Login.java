package fr.ujm.tse.Scream.Controller;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Login {





	public static void configureLogging() {
		// Create the appender that will write log messages to the 		
		FileAppender fileAppender = new FileAppender();
		fileAppender.setFile("logginErrors.txt");

		// Define the pattern of log messages.
		// Insert the string "%c{1}:%L" to also show class name and line.
		String pattern = "%d{yyyy-MM-dd HH:mm:ss} %-5p - %m%n";
		fileAppender.setLayout(new PatternLayout(pattern));


		fileAppender.activateOptions();
		Logger.getRootLogger().addAppender(fileAppender);
	}





}
