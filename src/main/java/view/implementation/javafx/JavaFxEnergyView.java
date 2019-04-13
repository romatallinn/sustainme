package view.implementation.javafx;

import controller.EnergyController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import view.interfaces.IEnergyView;

import java.io.IOException;

public class JavaFxEnergyView extends JavaFxView implements IEnergyView {

    private EnergyController controller;

    @FXML
    private Button homeBtn;

    @FXML
    private TextField solarArea;

    @FXML
    private TextField temperature;

    @FXML
    private Text currentArea;

    @FXML
    private Text currentTemp;

    @Override
    public void initView(EnergyController controller) {
        this.controller = controller;
    }

    @Override
    public void updateLabels() {
        controller.updateViewWithData();
    }

    @FXML
    private void goToHome() throws IOException {
        switchScene(homeBtn.getScene(), "home");
    }

    @FXML
    private void addSolarArea() {
        controller.addSolarArea(solarArea.getText());
    }

    @FXML
    private void changeTemperature() {
        controller.changeTemperature(temperature.getText());
    }

    @Override
    public void displayStatus(String msg) {
        System.out.println(msg);
    }

    @Override
    public void updateTemperature(double temp) {
        currentTemp.setText(Double.toString(temp));
    }

    @Override
    public void updateSolarArea(int area) {
        currentArea.setText(Integer.toString(area));
    }
}
