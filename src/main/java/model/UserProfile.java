package model;

import static java.lang.Math.pow;

import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.model.FirebaseResponse;
import net.thegreshams.firebase4j.service.Firebase;
import supporting.DatabaseConnection;

import java.io.UnsupportedEncodingException;
import java.util.Map;



/**
 * The class representing a user profile.
 */
public class UserProfile {

    private String authToken = "";
    private String uid = "";

    private String firstName = "";
    private String lastName = "";
    private String emailAddress = "";
    private int level = 0;
    private int experience = 0;
    private int exp = 0;
    private double co2Reduction = 0;                //Reduced carbon emission in kg CO2

    private Firebase connection;

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
        exp = 0;
        co2Reduction = 0;
    }


    /**
     * Empty constructor.
     */
    public UserProfile() {}

    /**
     * Initialization method used to make the Singleton user with placeholder data.
     */
    public void init() {
        firstName = "Roderick";
        lastName = "de Britto Heemskerk";
        emailAddress = "Roderickmbh@gmail.com";
        level = 1;
        experience = 0;
        exp = 0;
        co2Reduction = 0;

        checkLevel();
    }

    /**
     * Initialization method used to make the Singleton user with data from DB.
     * @param token auth token used by Firebase to verify user.
     */
    public void init(String email, String uid, String token) throws FirebaseException {

        this.emailAddress = email;
        this.uid = uid;
        this.authToken = token;
        this.level = 1;

        DatabaseConnection.init(token);
        connection = DatabaseConnection.getInstance();

        try {

            FirebaseResponse response = connection.get("users/" + uid );

            if (response.getSuccess()) {
                Map<String, Object> data = response.getBody();

                this.firstName = (String)data.get("fname");
                this.lastName = (String)data.get("lname");
                this.experience = (int)data.get("experience");

                Object co2redObj = data.get("co2red");

                if(co2redObj instanceof Double) {
                    this.co2Reduction = (double) co2redObj;
                } else {
                    this.co2Reduction = 0;
                }

                checkLevel();

            }


        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }


    /**
     * Increases score, calls CheckLevel.
     * @param score - the amount by which the experience needs to be increased.
     */
    public void increaseScore(int score) throws UnsupportedEncodingException,
            FirebaseException {

        experience += score;
        this.checkLevel();

        if(connection != null) {
            String patchData = String.format("{\"experience\":%s}", experience);
            connection.patch("users/" + uid, patchData);
        }

    }

    /**
     * Increases the attribute co2Reduction.
     * @param red -amount of CO2 reduced.
     */
    public void reduceCo2(double red) throws UnsupportedEncodingException,
            FirebaseException {
        co2Reduction += red;

        if(connection != null) {
            String patchData = String.format("{\"co2red\":%s}", co2Reduction);
            connection.patch("users/" + uid, patchData);
        }
    }


    /**
     * Checks current experience to see if the user should level up.
     */
    private void checkLevel() {
        this.exp = this.experience;
        while (exp >= 10 * pow(2, level - 1)) {
            exp -= 10 * pow(2, level - 1);
            level++;
        }
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

    public double getExp() {
        return (double)exp / (pow(2, level - 1)*10);
    }

    public double getCo2Reduction() {
        return co2Reduction;
    }

    public int getExperience() {
        return experience;
    }
}
