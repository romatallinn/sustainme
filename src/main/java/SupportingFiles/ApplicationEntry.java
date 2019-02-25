package SupportingFiles;


import Controller.DemoGUIController;
import Controller.DemoTerminalController;
import SupportingFiles.AppConfig;
import View.GUIView;
import javafx.application.Application;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;


import java.util.Collections;
import java.util.List;


/**
 * The entry point of the whole application; the very first class to be called.
 */
public class ApplicationEntry {

    /**
     * Entry point of the application
     * @param args - not used
     */
    public static void main(String[] args) {

        // Disable console debugging if the config file says so.
        if(!AppConfig.debug)
            DisableLogging();


        // Indicate that the application is setup and running.
        System.out.println("Application is running...\n\n");


        // Instantiate the initial controller of the application
        DemoGUIController controller = new DemoGUIController();
        Application.launch(GUIView.class);

    }


    /**
     * Disables Log4j logging into the console.
     */
    public static void DisableLogging()
    {
        List<Logger> loggers = Collections.<Logger>list(LogManager.getCurrentLoggers());
        loggers.add(LogManager.getRootLogger());
        for ( Logger logger : loggers ) {
            logger.setLevel(Level.OFF);
        }
    }

}
