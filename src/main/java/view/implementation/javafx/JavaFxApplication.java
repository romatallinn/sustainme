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
    public static HashMap<String, SceneFX> scenes;

    public static void launchApp(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        initScenes();

        SceneFX initialScene = JavaFxApplication.scenes.get("signup");


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

        SceneFX dummy;

        scenes = new HashMap<>();

        // Initialization of the sign up view & view
        ISignUpView signUpView = new JavaFxSignUpView();
        SignUpController signUpController = new SignUpController(signUpView);
        signUpView.initView(signUpController);

        dummy = new SceneFX<ISignUpView>("SustainMe - Sign Up", "/fxml/JavaFXSignUpView.fxml", "/css/SignUpIn.css");
        dummy.setView(signUpView);
        scenes.put("signup", dummy);


        // Initialization of the sign in view & view
        ILoginView signInView = new JavaFxSignInView();
        SignInController signIncontroller = new SignInController(signInView);
        signInView.initView(signIncontroller);

        dummy = new SceneFX<ILoginView>("SustainMe - Sign In", "/fxml/JavaFXSignInView.fxml", "/css/SignUpIn.css");
        dummy.setView(signInView);
        scenes.put("signin", dummy);



//        dummy = new SceneFX<>("homescreen");
//        dummy.init("SustainMe - Home", "/fxml/JavaFXHomescreenView.fxml", "");
//        scenes.add(dummy);

    }


    /**
     * @param sceneId - the key to quickly find the needed scene.
     * @return the scene that is represented by the inputted key.
     */
    public static SceneFX getSceneFX(String sceneId) {

        return JavaFxApplication.scenes.get(sceneId);

    }



}

/**
 * Helper class that helps to initialize the data of all scenes in the application for easier use.
 * @param <T> one of the view classes (packages: view -> implementation -> javafx)
 */
class SceneFX<T>{

    private String title;
    private String fxmlPath;
    private String cssPath;

    private T view;


    /**
     * Constructor for the scene
     * @param title - title of the scene displayed in the upper-left corner (window title).
     * @param fxmlPath - path to the file that stores the view markup.
     * @param cssPath - css file that will be connected to the view.
     */
    public SceneFX(String title, String fxmlPath, String cssPath) {
        this.title = title;
        this.fxmlPath = fxmlPath;
        this.cssPath = cssPath;
    }

    /**
     * Initializes the view (fxml's controller) file for the scene; all GUI events will be forwarded there.
     * @param view one instance of the view classes (packages: view -> implementation -> javafx)
     */
    public void setView(T view) {
        this.view = view;
    }

    /**
     * @return title of the window.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @return path to the fxml file of the view.
     */
    public String getFxmlPath() {
        return this.fxmlPath;
    }

    /**
     * @return path to the css file of the view.
     */
    public String getCssPath() {
        return this.cssPath;
    }

    /**
     * @return the view file (fxml's controller) that will handle all GUI events.
     */
    public T getView() {
        return this.view;
    }


}
