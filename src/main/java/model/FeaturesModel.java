package model;

import model.objects.UserProfile;

public class FeaturesModel {

    private int vegMealCounter;

    public FeaturesModel() {
        vegMealCounter = 0;
    }

    /**
     * Increases the User's reduced CO2 and score based on the amount of vegetarian meals eaten.
     * @param amount - The amount of meals eaten
     */
    public void vegMeal(int amount) {
        UserProfile.getInstance().increaseExp(5 * amount);
        UserProfile.getInstance().reduceCo2(3.0 * amount);
        vegMealCounter += amount;
    }

    public int getVegMealCounter() {
        return vegMealCounter;
    }

}
