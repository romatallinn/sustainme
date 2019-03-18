package view.implementation.javafx;

import controller.FoodController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import view.interfaces.IFoodView;


public class JavaFxFoodView extends JavaFxView implements IFoodView {

    private FoodController controller;

    @FXML
    private TextField mealsCount;

    @Override
    public void initView(FoodController controller) {
        this.controller = controller;
    }

    @FXML
    private void addEatedVegMeals() {

        int i = Integer.parseInt(mealsCount.getText());
        controller.addEatedVegMeals(i);

    }

}
