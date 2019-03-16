package model;

public class FeatureClass {
    /**
     * Increases the User's reduced CO2 and score based on the amount of vegetarian meals eaten.
     * @param amount - The amount of meals eaten
     */
    public static void vegMeal(int amount) {
        SingletonUser.getInstance().increaseScore(5 * amount);
        SingletonUser.getInstance().reduceCo2(3.0 * amount);
    }
}
