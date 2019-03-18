package model;

import net.thegreshams.firebase4j.error.FirebaseException;

import java.io.UnsupportedEncodingException;

public class FeaturesModel {
    private int vegMealCounter;

    public FeaturesModel() {
        vegMealCounter = 0;
    }

    /**
     * Increases the User's reduced CO2 and score based on the amount of vegetarian meals eaten.
     * @param amount - The amount of meals eaten
     */
    public void vegMeal(int amount) throws FirebaseException, UnsupportedEncodingException {
        SingletonUser.getInstance().increaseScore(5 * amount);
        SingletonUser.getInstance().reduceCo2(3.0 * amount);
        vegMealCounter += amount;
    }

    public int getVegMealCounter() {
        return vegMealCounter;
    }
}
