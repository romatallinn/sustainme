package model;

import static java.lang.Math.pow;

import model.objects.UserData;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import supporting.ServerApi;
import view.element.WindowsNotifications;

import java.awt.AWTException;
import java.net.MalformedURLException;


/**
 * The class representing a user profile.
 */
public class UserProfile {


    private static UserProfile instance = new UserProfile();

    public Boolean badgeLevel = false;
    public Boolean badgeCo2 = false;
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

        calculateLevel();

    }

    public void clean() {
        this.authToken = "";
        this.data = new UserData();
    }

    /**
     * Retrieving the instance of UserProfile.
     *
     * @return UserProfile object of the client's user.
     */
    public static UserProfile getInstance() {
        return instance;
    }


    /**
     * Initialization method used to make the Singleton user with data from DB.
     *
     * @param token auth token used by Firebase to verify user.
     */
    public void init(String email, String uid, String token) {

        authToken = token;

        final String uri = ServerApi.HOST + ServerApi.RETRIEVE_USER;
        RestTemplate restTemplate = new RestTemplate();

        try {
            data = restTemplate.postForObject(uri, uid, UserData.class);
            badgeLevel = new BadgeModel().receiveBadge("levelHundredBadge");
            badgeCo2 = new BadgeModel().receiveBadge("co2ReducedBadge");
        } catch (RestClientException e) {
            System.out.println("Exception: " + e.getLocalizedMessage());
            data = new UserData();
        }

        data.emailAddress = email;
        data.uid = uid;
        data.level = 1;

        calculateLevel();

    }


    /**
     * Sets the local experience amount, then distrubutes the level.
     *
     * @param exp - new value of experience.
     */
    public void setLocalExp(int exp) {

        data.experience = exp;
        this.calculateLevel();

    }

    /**
     * Sets the local attribute of the amount of the reduced CO2.
     *
     * @param red - new value of CO2 reduction.
     */
    public void setLocalCo2Stats(double red) {
        data.co2red = red;
        checkBadgeCo2Reduction();
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
    private void calculateLevel() {

        data.calculateLevel();

        checkBadgeLevelHundred();

    }


    public String getUid() {
        return data.uid;
    }

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
        return (double) data.experience / (pow(2, data.level - 1) * 10);
    }

    /**
     * Checks if the badge is visible otherwise it makes the badge visible and initiates
     * a notification.
     */
    public boolean checkBadgeCo2Reduction() {

        if (badgeCo2) {
            return true;
        } else if (getCo2Reduction() >= 100) {

            new BadgeModel().updateBadge("co2ReducedBadge");
            try {
                new WindowsNotifications().notification(
                    "/badges/4.png",
                    "Congrats! You have reduced 100kg of CO2!");
            } catch (AWTException | MalformedURLException e) {
                e.printStackTrace();
            }

            return true;
        }

        return  false;

    }

    /**
     * Checks if the badge is visible otherwise it makes the badge visible and initiates
     * a notification.
     */
    public boolean checkBadgeLevelHundred() {

        if (badgeLevel) {
            return true;
        } else if (getLevel() >= 25) {

            new BadgeModel().updateBadge("levelHundredBadge");
            try {
                new WindowsNotifications().notification(
                    "/badges/6.png",
                    "Congrats! You are at level 100!");
            } catch (AWTException | MalformedURLException e) {
                e.printStackTrace();
            }

            return true;
        }

        return  false;
    }


}
