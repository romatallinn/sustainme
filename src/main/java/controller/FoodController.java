package controller;

import model.FoodModel;
import view.interfaces.IFoodView;


public class FoodController {

    private IFoodView view;
    private FoodModel model;

    /**
     * Constructor for the class.
     * @param view that is represented by the controller.
     */
    public FoodController(IFoodView view) {
        this.view = view;
        this.model = new FoodModel();
    }

    /**
     * Retrieves amount of meals eaten and sends this to the view to update.
     */
    public void updateViewWithData() {

        int vegmeals = model.getVegMealsCount();
        view.updateVegCounter(vegmeals);

        double local = model.getLocalProduceCount();
        view.updateLocalProduceCounter(local);

    }

    /**
     * The method acts upon the fact of the eaten vegetarian meals.
     * @param countString of the vegetarian meals eaten.
     */
    public void addEatenVegMeals(String countString) {
        int count = 0;
        try {
            count = Integer.parseInt(countString);
            if (count < 0 || count > 3) {
                view.displayStatusVegetarian("Please enter a  number in the range 0-3 meals.");
                return;
            }
            model.addEatenVegMeal(count);
            view.displayStatusVegetarian("The stat of the vegetarian meals is updated!");
            updateViewWithData();
        } catch (NumberFormatException e) {
            view.displayStatusVegetarian("Please enter an integer number.");
        }
    }


    /**
     * The method acts upon the fact of the eaten local produces.
     * @param weightString - weight of the eaten produces in kg.
     */
    public void addEatenLocalProduce(String weightString) {
        float kg = 0;
        try {
            kg = Float.parseFloat(weightString);
            if (kg < 0 || kg > 10) {
                view.displayStatusLocal("Please enter the weight in the range 0-10kg.");
                return;
            }
            model.addEatenLocalProduce(kg);
            view.displayStatusLocal("The stat of the local produce is updated!");
            updateViewWithData();
        } catch (NumberFormatException e) {
            view.displayStatusLocal("Please enter a number.");
        }

    }

}
