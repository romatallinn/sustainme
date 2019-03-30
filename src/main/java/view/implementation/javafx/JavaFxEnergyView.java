package view.implementation.javafx;

import controller.EnergyController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.interfaces.IEnergyView;

import java.io.IOException;


public class JavaFxEnergyView extends JavaFxView implements IEnergyView {

    private EnergyController controller;

    @FXML
    private Button homeBtn;

    @Override
    public void initView(EnergyController controller) {
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

    @Override
    public void updateSolarPanelArea(int area) {
        // TODO
    }
}
