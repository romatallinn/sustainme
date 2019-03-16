package view.implementation.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class JavaFxView {

    protected void switchScene(Scene scene, String nextSceneId) throws IOException {

        SceneFx nextScene = JavaFxApplication.getSceneFx(nextSceneId);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(nextScene.getFxmlPath()));
        loader.setController(nextScene.getView());

        Parent root = loader.load();
        scene.setRoot(root);

    }

}
