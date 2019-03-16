package view.implementation.javafx;

import controller.SignInController;
import controller.SignUpController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import view.interfaces.ILoginView;
import view.interfaces.ISignUpView;

import java.util.HashMap;

public class JavaFxApplication extends Application {

    /**
     * Map of all the scenes in the application.
     */
    public static HashMap<String, SceneFx> scenes;

    public static void launchApp(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        initScenes();

        SceneFx initialScene = JavaFxApplication.scenes.get("signup");


        FXMLLoader loader = new FXMLLoader(getClass().getResource(initialScene.getFxmlPath()));

        loader.setController(initialScene.getView());

        Parent root = (Parent)loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource(initialScene.getCssPath()).toString());

        primaryStage.setTitle(initialScene.getTitle());
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    /**
     * Initializes the map of all scenes that will be available in the application.
     */
    private static void initScenes() {

        SceneFx dummy;

        scenes = new HashMap<>();

        // Initialization of the sign up view & view
        ISignUpView signUpView = new JavaFxSignUpView();
        SignUpController signUpController = new SignUpController(signUpView);
        signUpView.initView(signUpController);

        dummy = new SceneFx<ISignUpView>("SustainMe - Sign Up", "/fxml/JavaFXSignUpView.fxml",
                "/css/SignUpIn.css");
        dummy.setView(signUpView);
        scenes.put("signup", dummy);


        // Initialization of the sign in view & view
        ILoginView signInView = new JavaFxSignInView();
        SignInController signIncontroller = new SignInController(signInView);
        signInView.initView(signIncontroller);

        dummy = new SceneFx<ILoginView>("SustainMe - Sign In", "/fxml/JavaFXSignInView.fxml",
                "/css/SignUpIn.css");
        dummy.setView(signInView);
        scenes.put("signin", dummy);



        //dummy = new SceneFX<>("homescreen");
        //dummy.init("SustainMe - Home", "/fxml/JavaFXHomescreenView.fxml", "");
        //scenes.add(dummy);

    }


    /**
     * @param sceneId - the key to quickly find the needed scene.
     * @return the scene that is represented by the inputted key.
     */
    public static SceneFx getSceneFx(String sceneId) {

        return JavaFxApplication.scenes.get(sceneId);

    }



}



