package view.implementation.javafx;

import controller.VegMealController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import view.interfaces.IVegMealView;


public class JavaFxVegMealView extends JavaFxView implements IVegMealView {

    private VegMealController controller;

    @FXML
    private TextField mealsCount;

    @Override
    public void initView(VegMealController controller) {
        this.controller = controller;
    }

    @FXML
    private void addEatedVegMeals() {

        int i = Integer.parseInt(mealsCount.getText());
        controller.addEatedVegMeals(i);

    }

}
