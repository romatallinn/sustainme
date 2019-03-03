package supporting;

import controller.DemoController;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import view.implementation.javafx.JavaFxDemoView;
import view.interfaces.IDemoView;

import java.util.Collections;
import java.util.List;


/**
 * The entry point of the whole application; the very first class to be called.
 */
public class ApplicationEntry extends Application {

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

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Instantiate the initial controller of the application
        IDemoView view = new JavaFxDemoView();
        DemoController controller = new DemoController(view);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JavaFXDemoView.fxml"));

        view.initView(controller);
        loader.setController(view);

        Parent root = (Parent)loader.load();

        primaryStage.setTitle("Demo Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.show();
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
