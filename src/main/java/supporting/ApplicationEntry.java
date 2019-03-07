package supporting;

import controller.SignUpController;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import view.implementation.javafx.JavaFxSignUpView;
import view.interfaces.ISignUpView;

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
        ISignUpView view = new JavaFxSignUpView();
        SignUpController controller = new SignUpController(view);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JavaFXHomeView.fxml"));

        view.initView(controller);
        loader.setController(view);

        Parent root = (Parent)loader.load();

        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("/css/Home.css").toString());

        primaryStage.setTitle("SustainMe - Login");
        primaryStage.setScene(scene);
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
