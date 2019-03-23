package model.objects;

import static java.lang.Math.pow;

/**
 * The class representing a user profile.
 */
public class UserProfile {


    private static UserProfile instance = new UserProfile();

    public String authToken = "";
    private UserData data;


    /**
     * Constructor with placeholder data.
     * Used only for testing purposes.
     */
    public UserProfile() {

        data = new UserData();

        data.fname = "Firstname";
        data.lname = "Lastname";
        data.emailAddress = "test@test.com";
        data.level = 1;
        data.experience = 15;
        data.co2red = 5;

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

        authToken = token;

        // TODO: Retrieve UserData object from Server. Assign it to this.data.

        data.emailAddress = email;
        data.uid = uid;
        data.level = 1;

        checkLevel();

    }


    /**
     * Increases score, calls CheckLevel.
     * @param score - the amount by which the experience needs to be increased.
     */
    public void increaseExp(int score) {

        data.experience += score;
        this.checkLevel();

        // TODO: Request Server to Increase Experience by Amount.

    }

    /**
     * Increases the attribute co2red.
     * @param red -amount of CO2 reduced.
     */
    public void reduceCo2(double red) {

        data.co2red += red;

        // TODO: Request Server to Reduce CO2 by Amount.

    }

    /**
     * Signs user off from the session.
     */
    public void logout() {

        clean();

        // TODO: Kill Session with Server.

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


    public String getUid() { return data.uid; }

    public String getFirstName() {
        return data.fname;
    }

    public String getLastName() {
        return data.lname;
    }

    public String getEmailAddress() {
        return data.emailAddress;
    }

    public int getLevel() {
        return data.level;
    }

    public double getCo2Reduction() {
        return data.co2red;
    }

    public int getExperience() {
        return data.experience;
    }

    public double getExpProgress() {
        return (double)data.experience / (pow(2, data.level - 1) * 10);
    }

}
