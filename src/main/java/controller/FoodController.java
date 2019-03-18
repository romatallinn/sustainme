package controller;

import model.FeaturesModel;
import net.thegreshams.firebase4j.error.FirebaseException;
import view.interfaces.IFoodView;

import java.io.UnsupportedEncodingException;

public class FoodController {

    private IFoodView view;
    private FeaturesModel model;

    public FoodController(IFoodView view) {
        this.view = view;
        this.model = new FeaturesModel();
    }

    public void addEatedVegMeals(int count) {

        try {
            model.vegMeal(count);
            view.displayStatus("The stat is updated!");
        } catch (FirebaseException | UnsupportedEncodingException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

}
