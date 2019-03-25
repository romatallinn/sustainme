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
        vegCounter.setText("Counter: " + counter);
    }
}
