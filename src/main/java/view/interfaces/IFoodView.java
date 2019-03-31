package view.interfaces;

import controller.FoodController;

public interface IFoodView {

    void initView(FoodController controller);

    void displayStatus(String msg);

    void updateVegCounter(int counter);

    void updateLocalProduceCounter(double kg);

}
