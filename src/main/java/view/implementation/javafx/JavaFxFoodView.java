package view.implementation.javafx;

import controller.FoodController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import view.interfaces.IFoodView;

import java.io.IOException;


public class JavaFxFoodView extends JavaFxView implements IFoodView {

    private FoodController controller;

    @FXML
    private Button homeBtn;

    @FXML
    private TextField vegMealsCount;

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

        int count = Integer.parseInt(vegMealsCount.getText());
        controller.addEatenVegMeals(count);

    }

    @Override
    public void displayStatus(String msg) {
        // TODO
    }
}
