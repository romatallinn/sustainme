package view.implementation.javafx;

import controller.FoodController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import view.interfaces.IFoodView;

import java.io.IOException;


public class JavaFxFoodView extends JavaFxView implements IFoodView {

    private FoodController controller;

    @FXML
    private Button homeBtn;

    @FXML
    private TextField vegMealsCount;

    @FXML
    private Text vegCounter;

    @FXML
    private TextField localCount;

    @FXML
    private Text localCounter;

    @Override
    public void initView(FoodController controller) {
        this.controller = controller;
    }

    @FXML
    private void goToHome() throws IOException {
        switchScene(homeBtn.getScene(), "home");
    }

    @FXML
    private void addEatenVegMeals() {

        controller.addEatenVegMeals(vegMealsCount.getText());

    }

    @FXML
    private void addEatenLocalProduce() {
        controller.addEatenLocalProduce(localCount.getText());
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
    public void updateVegCounter(int counter) {
        vegCounter.setText(Integer.toString(counter));
    }

    @Override
    public void updateLocalProduceCounter(double kg) {
        localCounter.setText(Double.toString(kg));
    }
}
