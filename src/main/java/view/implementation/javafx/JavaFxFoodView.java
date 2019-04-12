package view.implementation.javafx;

import controller.FoodController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import view.interfaces.IFoodView;

import java.io.IOException;
import java.text.DecimalFormat;


public class JavaFxFoodView extends JavaFxView implements IFoodView {

    private FoodController controller;

    @FXML
    private Button homeBtn;

    @FXML
    private TextField vegMealsCountInput;

    @FXML
    private Text vegCounter;

    @FXML
    private TextField localCountInput;

    @FXML
    private Text localCounter;

    @FXML
    private Text statusVeg;

    @FXML
    private Text statusLoc;

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
        controller.addEatenVegMeals(vegMealsCountInput.getText());
        vegMealsCountInput.clear();
    }

    @FXML
    private void addEatenLocalProduce() {
        controller.addEatenLocalProduce(localCountInput.getText());
        localCountInput.clear();
    }

    @Override
    public void displayStatusVegetarian(String msg) {
        statusVeg.setText(msg);
        System.out.println(msg);
    }

    @Override
    public void displayStatusLocal(String msg) {
        statusLoc.setText(msg);
        System.out.println(msg);
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
        String str = new DecimalFormat("#.##").format(kg);
        localCounter.setText(str);
    }
}
