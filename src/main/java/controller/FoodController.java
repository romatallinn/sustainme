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
     * @param countString of the vegetarian meals eaten.
     */
    public void addEatenVegMeals(String countString) {
        int count = 0;
        try {
            count = Integer.parseInt(countString);
            if (count < 0 || count > 3) {
                view.displayStatus("Please enter a  number in the range 0-3");
                return;
            }
            model.vegMeal(count);
            view.displayStatus("The stat is updated!");
            updateViewWithData();
        } catch (NumberFormatException e) {
            view.displayStatus("Please enter an integer number");
        }
    }

}
