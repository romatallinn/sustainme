package model.objects;

import org.springframework.web.client.RestTemplate;
import supporting.ServerAPI;


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

        final String uri = ServerAPI.HOST + ServerAPI.RETRIEVE_USER;
        RestTemplate restTemplate = new RestTemplate();

        data = restTemplate.postForObject(uri, uid, UserData.class);

        data.emailAddress = email;
        data.uid = uid;
        data.level = 1;

        checkLevel();

    }


    /**
     * Sets the local experience amount, then distrubutes the level.
     * @param exp - new value of experience.
     */
    public void setLocalExp(int exp) {

        data.experience = exp;
        this.checkLevel();

    }

    /**
     * Sets the local attribute of the amount of the reduced CO2.
     * @param red - new value of CO2 reduction.
     */
    public void setLocalCo2Stats(double red) {
        data.co2red = red;
    }

    /**
     * Sets the local counter of the veg meals eaten.
     * @param amount - new value for the veg meals eaten counter.
     */
    public void setLocalVegMealsCounter(int amount){
        data.vegmeals = amount;
    }

    /**
     * Signs user off from the session.
     */
    public void logout() {
        clean();
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

    public int getVegMeals() { return data.vegmeals; }

}
