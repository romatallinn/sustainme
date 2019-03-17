package view.implementation.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class JavaFxDemoView extends Application {


    public static void launchApp(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JavaFXDemoView.fxml"));

        Parent root = (Parent)loader.load();

        Scene scene = new Scene(root);

        primaryStage.setTitle("SustainMe - Login");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

}
