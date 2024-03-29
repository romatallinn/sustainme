package view.implementation.javafx;

import controller.BadgesController;
import controller.EnergyController;
import controller.FoodController;
import controller.FriendsComparisonController;
import controller.FriendsController;
import controller.HomescreenController;
import controller.RecyclingController;
import controller.SignInController;
import controller.SignUpController;
import controller.TransportController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import view.interfaces.IBadgesView;
import view.interfaces.IEnergyView;
import view.interfaces.IFoodView;
import view.interfaces.IFriendView;
import view.interfaces.IFriendsComparisonView;
import view.interfaces.IHomeView;
import view.interfaces.IRecyclingView;
import view.interfaces.ISignInView;
import view.interfaces.ISignUpView;
import view.interfaces.ITransportView;


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

        Parent root = (Parent) loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource(initialScene.getCssPath()).toString());

        primaryStage.setTitle("SustainMe - Green and Happy!");
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

        dummy = new SceneFx<ISignUpView>("/fxml/JavaFXSignUpView.fxml",
            "/css/SignUpInView.css");
        dummy.setView(signUpView);
        scenes.put("signup", dummy);

        //-----

        // Initialization of the sign in view & view
        ISignInView signInView = new JavaFxSignInView();
        SignInController signIncontroller = new SignInController(signInView);
        signInView.initView(signIncontroller);

        dummy = new SceneFx<ISignInView>("/fxml/JavaFXSignInView.fxml",
            "/css/SignUpInView.css");
        dummy.setView(signInView);
        scenes.put("signin", dummy);

        //-----

        IHomeView homeView = new JavaFxHomeView();
        HomescreenController homescreenController = new HomescreenController(homeView);
        homeView.initView(homescreenController);

        dummy = new SceneFx<IHomeView>( "/fxml/JavaFXHomeView.fxml",
            "/css/HomeView.css");
        dummy.setView(homeView);
        scenes.put("home", dummy);

        //-----

        IFoodView foodView = new JavaFxFoodView();
        FoodController vegController = new FoodController(foodView);
        foodView.initView(vegController);

        dummy = new SceneFx<IFoodView>("/fxml/JavaFXFoodView.fxml",
            "/css/FoodView.css");
        dummy.setView(foodView);
        scenes.put("food", dummy);

        //----

        ITransportView transportView = new JavaFxTransportView();
        TransportController transportController = new TransportController(transportView);
        transportView.initView(transportController);

        dummy = new SceneFx<ITransportView>(
            "/fxml/JavaFXTransportView.fxml", "/css/TransportView.css");
        dummy.setView(transportView);
        scenes.put("transport", dummy);

        //-----

        IEnergyView energyView = new JavaFxEnergyView();
        EnergyController energyController = new EnergyController(energyView);
        energyView.initView(energyController);

        dummy = new SceneFx<IEnergyView>("/fxml/JavaFXEnergyView.fxml",
                 "/css/EnergyView.css");
        dummy.setView(energyView);
        scenes.put("energy", dummy);

        //-----

        IFriendView friendsView = new JavaFxFriendsView();
        FriendsController friendsController = new FriendsController(friendsView);
        friendsView.initView(friendsController);

        dummy = new SceneFx<IFriendView>("/fxml/JavaFXFriendsView.fxml",
            "/css/FriendsView.css");
        dummy.setView(friendsView);
        scenes.put("friends", dummy);

        //-----

        IRecyclingView paperRecyclingView = new JavaFxRecyclingView();
        RecyclingController recyclingController =
            new RecyclingController(paperRecyclingView);
        paperRecyclingView.initView(recyclingController);

        dummy = new SceneFx<IRecyclingView>(
                "/fxml/JavaFXRecyclingView.fxml",
            "/css/RecyclingView.css");
        dummy.setView(paperRecyclingView);
        scenes.put("recycling", dummy);

        //----


        IFriendsComparisonView friendsCompareView = new JavaFxFriendsComparisonView();

        FriendsComparisonController friendController =
            new FriendsComparisonController(friendsCompareView);

        friendsCompareView.initView(friendController);

        dummy = new SceneFx<IFriendsComparisonView>(
            "/fxml/JavaFXFriendsComparisonView.fxml",
            "/css/FriendsComparisonView.css");

        dummy.setView(friendsCompareView);
        scenes.put("friendsComparison", dummy);

        //-----

        IBadgesView badgesView = new JavaFxBadgesView();
        BadgesController badgesController = new BadgesController(badgesView);
        badgesView.initView(badgesController);

        dummy = new SceneFx<IBadgesView>("/fxml/JavaFXBadgesView.fxml",
                "/css/BadgesView.css");
        dummy.setView(badgesView);
        scenes.put("badges", dummy);

        //-----

        JavaFxFractalTreeView fractalTreeView = new JavaFxFractalTreeView();

        dummy = new SceneFx<JavaFxFractalTreeView>(
                "/fxml/JavaFxFractalTreeView.fxml",
                "/css/FractalTreeView.css");

        dummy.setView(fractalTreeView);
        scenes.put("fractalTree", dummy);

    }


    /**
     * @param sceneId - the key to quickly find the needed scene.
     * @return the scene that is represented by the inputted key.
     */
    public static SceneFx getSceneFx(String sceneId) {

        return JavaFxApplication.scenes.get(sceneId);

    }


}



