package controller;

import model.FeaturesModel;
import model.objects.UserProfile;
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
     * Retrieves amount of meals eaten and sends this to the view to update.
     */
    public void updateViewWithData() {
        int vegmeals = UserProfile.getInstance().getVegMeals();
        view.updateVegCounter(vegmeals);

    }

    /**
     * The method acts upon the fact of the eaten vegetarian meals.
     * @param count of the vegetarian meals eaten.
     */
    public void addEatenVegMeals(int count) {

        model.vegMeal(count);
        view.displayStatus("The stat is updated!");
        updateViewWithData();
    }

}
