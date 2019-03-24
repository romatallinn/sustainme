package view.implementation.javafx;

import controller.TransportController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.interfaces.ITransportView;

import java.io.IOException;


public class JavaFxTransportView extends JavaFxView implements ITransportView {

    private TransportController controller;

    @FXML
    private Button homeBtn;

    @Override
    public void initView(TransportController controller) {
        this.controller = controller;
    }

    @FXML
    private void goToHome() throws IOException {
        switchScene(homeBtn.getScene(), "home");
    }

    @Override
    public void displayStatus(String msg) {
        // TODO
    }
}
