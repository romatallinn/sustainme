package controller;

import model.FeaturesModel;
import view.interfaces.IFoodView;


public class FoodController {

    private IFoodView view;
    private FeaturesModel model;

    /**
     * Constructor for the class.
     * @param view that is represented by the controller.
     */
    public FoodController(IFoodView view) {
        this.view = view;
        this.model = new FeaturesModel();
    }

    /**
     * The method acts upon the fact of the eaten vegetarian meals.
     * @param count of the vegetarian meals eaten.
     */
    public void addEatenVegMeals(int count) {

        model.vegMeal(count);
        view.displayStatus("The stat is updated!");

    }

}
