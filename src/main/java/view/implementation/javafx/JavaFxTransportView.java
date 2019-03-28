package view.implementation.javafx;

import controller.TransportController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import view.interfaces.ITransportView;

import java.io.IOException;


public class JavaFxTransportView extends JavaFxView implements ITransportView {

    private TransportController controller;

    @FXML
    private Button homeBtn;

    @FXML
    private TextField bikeDistance;

    @FXML
    private Text distanceBikeCounter;

    @Override
    public void initView(TransportController controller) {
        this.controller = controller;
    }

    @FXML
    private void goToHome() throws IOException {
        switchScene(homeBtn.getScene(), "home");
    }

    @FXML
    private void addCycledKilometers(){
        controller.addBikeKms(bikeDistance.getText());
    }

    @Override
    public void displayStatus(String msg) {
        // TODO
    }

    @Override
    protected void updateLabels() {
        controller.updateViewWithData();
    }

    @Override
    public void updateBikeDistance(int distance) {
        distanceBikeCounter.setText(Integer.toString(distance));
    }
}
