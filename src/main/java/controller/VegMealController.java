package controller;

import model.FeaturesModel;
import net.thegreshams.firebase4j.error.FirebaseException;
import view.interfaces.IVegMealView;

import java.io.UnsupportedEncodingException;

public class VegMealController {

    private IVegMealView view;
    private FeaturesModel model;

    public VegMealController(IVegMealView view) {
        this.view = view;
        this.model = new FeaturesModel();
    }

    public void addEatedVegMeals(int count) {

        try {
            model.vegMeal(count);
        } catch (FirebaseException | UnsupportedEncodingException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

}
