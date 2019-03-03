package Model;

import static java.lang.Math.pow;

/**
 *
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
     * @param first First name
     * @param last Surname
     * @param email Emailaddress
     */
    UserProfile(String first, String last, String email){
        firstName = first;
        lastName = last;
        emailAddress = email;
        level = 1;
        experience = 0;
        co2Reduction = 0;
    }

    /**
     * Increases score, calls CheckLevel
     * @param score Integer to increase score with
     */
    void IncreaseScore(int score){
        experience += score;
        this.CheckLevel();
    }

    /**
     * Increases the attribute co2Reduction
     * @param red Amount of CO2 reduced
     */
    void ReduceCo2(double red){
        co2Reduction += red;
    }


    /**
     * Checks current experience to see if the user shouldlevel up
     */
    private void CheckLevel(){
        while (experience >= 10*pow(2,level-1)) {
            experience -= 10 * pow(2, level - 1);
            level++;
        }
    }

    /**
     * Adds score based on eating a vegetarian meal
     */
    void VegMeal(){
        IncreaseScore(5);
        ReduceCo2(3.0);
    }


    String getFirstName(){
        return firstName;
    }
    String getLastName(){
        return lastName;
    }
    String getEmailAddress(){
        return emailAddress;
    }
    int getLevel(){
        return level;
    }
    int getExperience(){
        return experience;
    }
    double getCo2Reduction(){return co2Reduction;}

}
