package view.implementation.javafx;

import controller.SignUpController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import view.interfaces.ISignUpView;

public class JavaFxApplication extends Application {

    public static void launchApp(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Instantiate the initial controller of the application
        ISignUpView view = new JavaFxSignUpView();
        SignUpController controller = new SignUpController(view);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JavaFXSignUpView.fxml"));

        view.initView(controller);
        loader.setController(view);

        Parent root = (Parent)loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/SignUp_Login.css").toString());

        primaryStage.setTitle("SustainMe - Login");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

}
