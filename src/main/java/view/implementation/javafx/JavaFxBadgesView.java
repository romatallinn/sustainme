package view.implementation.javafx;

import controller.BadgesController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.interfaces.IBadgesView;

import java.io.IOException;

public class JavaFxBadgesView extends JavaFxView implements IBadgesView {

    private BadgesController controller;

    @FXML
    private Button homeBtn;

    @Override
    public void initView(BadgesController controller) {
        this.controller = controller;
    }

    @FXML
    private void goToHome() throws IOException {
        switchScene(homeBtn.getScene(), "home");
    }
}
