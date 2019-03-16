package view.implementation.javafx;

import controller.HomescreenController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import view.interfaces.IHomeView;

import java.io.IOException;

public class JavaFxHomeView extends JavaFxView implements IHomeView {

    private HomescreenController controller;

    @FXML
    private Button logout;
    @FXML
    private Text name;
    @FXML
    private Text level;
    @FXML
    private Button food;
    @FXML
    private Button clothes;
    @FXML
    private Button house;
    @FXML
    private Button transport;
    @FXML
    private Button news;
    @FXML
    private Button score;


    /**
     * Changes window from goToSignin view to sign up view.
     */
    @FXML
    public void goToSignIn() throws IOException {
        this.switchScene(logout.getScene(), "signin");
    }

    @Override
    public void initView(HomescreenController controller) {
        this.controller = controller;
    }


}
