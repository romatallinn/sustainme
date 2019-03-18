package controller;

import model.FeaturesModel;
import net.thegreshams.firebase4j.error.FirebaseException;
import view.interfaces.IFoodView;

import java.io.UnsupportedEncodingException;

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

        try {
            model.vegMeal(count);
            view.displayStatus("The stat is updated!");
        } catch (FirebaseException | UnsupportedEncodingException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

}
