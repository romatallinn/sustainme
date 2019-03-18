package view.implementation.javafx;

import controller.VegMealController;
import javafx.fxml.FXML;
import view.interfaces.IVegMealView;

import java.awt.*;

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
