package model.objects;

import static java.lang.Math.pow;

/**
 * The class representing a user profile.
 */
public class UserProfile {


    private static UserProfile instance = new UserProfile();

    private UserData data;


    /**
     * Constructor with placeholder data.
     * Used only for testing purposes.
     */
    public UserProfile() {

        data = new UserData();

        data.firstName = "Firstname";
        data.lastName = "Lastname";
        data.emailAddress = "test@test.com";
        data.level = 1;
        data.experience = 15;
        data.co2Reduction = 5;

        checkLevel();

    }

    public void clean() {
        UserProfile.instance = new UserProfile();
    }

    /**
     * Retrieving the instance of UserProfile.
     * @return UserProfile object of the client's user.
     */
    public static UserProfile getInstance() {
        return instance;
    }



    /**
     * Initialization method used to make the Singleton user with data from DB.
     * @param token auth token used by Firebase to verify user.
     */
    public void init(String email, String uid, String token) {

        data = new UserData();

        data.emailAddress = email;
        data.uid = uid;
        data.authToken = token;
        data.level = 1;

        // TODO: Server API, data load.

    }


    /**
     * Increases score, calls CheckLevel.
     * @param score - the amount by which the experience needs to be increased.
     */
    public void increaseExp(int score) {

        data.experience += score;
        this.checkLevel();

        // TODO: Request to Server to Increase Score by Amount

    }

    /**
     * Increases the attribute co2Reduction.
     * @param red -amount of CO2 reduced.
     */
    public void reduceCo2(double red) {

        data.co2Reduction += red;

        // TODO: Request to Server to Reduce CO2 by Amount

    }

    /**
     * Signs user off from the session.
     */
    public void logout() {

        clean();

        // TODO: Kill Session with Server

    }


    /**
     * Checks current experience to see if the user should level up.
     */
    private void checkLevel() {

        while (data.experience >= 10 * pow(2, data.level - 1)) {

            data.experience -= 10 * pow(2, data.level - 1);
            data.level++;

        }

    }


    public String getFirstName() {
        return data.firstName;
    }

    public String getLastName() {
        return data.lastName;
    }

    public String getEmailAddress() {
        return data.emailAddress;
    }

    public int getLevel() {
        return data.level;
    }

    public double getCo2Reduction() {
        return data.co2Reduction;
    }

    public int getExperience() {
        return data.experience;
    }

    public double getExpProgress() {
        return (double)data.experience / (pow(2, data.level - 1) * 10);
    }

}
