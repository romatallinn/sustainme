package supporting;


import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import view.implementation.javafx.JavaFxApplication;

import java.util.Collections;
import java.util.List;


/**
 * The entry point of the whole application; the very first class to be called.
 */
public class ApplicationEntry {

    /**
     * Entry point of the application.
     * @param args - not used.
     */
    public static void main(String[] args) {

        // Disable console debugging if the config file says so.
        if (!AppConfig.debug) {
            disableLogging();
        }

        // Indicate that the application is setup and running.
        System.out.println("Application is running...\n\n");

        JavaFxApplication.launchApp(args);
    }


    /**
     * Disables Log4j logging into the console.
     */
    public static void disableLogging() {

        List<Logger> loggers = Collections.<Logger>list(LogManager.getCurrentLoggers());
        loggers.add(LogManager.getRootLogger());
        for ( Logger logger : loggers ) {
            logger.setLevel(Level.OFF);
        }

    }

}
