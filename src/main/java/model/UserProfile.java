package model;

import static java.lang.Math.pow;

/**
 * The class representing a user profile.
 */
class UserProfile {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private int level;
    private int experience;
    private double co2Reduction;                //Reduced carbon emission in kg CO2

    /**
     * Constructor for the UserProfile class, defaults level to 1, and experience to 0
     * @param first - first name of the user.
     * @param last - last name of the user.
     * @param email - email address of the user.
     */
    public UserProfile(String first, String last, String email) {
        firstName = first;
        lastName = last;
        emailAddress = email;
        level = 1;
        experience = 0;
        co2Reduction = 0;
    }

    /**
     * Increases score, calls CheckLevel.
     * @param score - the amount by which the experience needs to be increased.
     */
    public void increaseScore(int score) {
        experience += score;
        this.checkLevel();
    }

    /**
     * Increases the attribute co2Reduction.
     * @param red -amount of CO2 reduced.
     */
    public void reduceCo2(double red) {
        co2Reduction += red;
    }


    /**
     * Checks current experience to see if the user shouldlevel up.
     */
    private void checkLevel() {
        while (experience >= 10 * pow(2, level - 1)) {
            experience -= 10 * pow(2, level - 1);
            level++;
        }
    }

    /**
     * Adds score based on eating a vegetarian meal.
     */
    void vegMeal() {
        increaseScore(5);
        reduceCo2(3.0);
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public double getCo2Reduction() {
        return co2Reduction;
    }

}
