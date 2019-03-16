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

    private String authToken;
    private String uid;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private int level;
    private int experience;
    private int exp;
    private double co2Reduction;                //Reduced carbon emission in kg CO2

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
    }

    /**
     * Initialization method used to make the Singleton user with data from DB.
     * @param token auth token used by Firebase to verify user.
     */
    public void init(String email, String uid, String token) throws FirebaseException {

        this.emailAddress = email;
        this.uid = uid;
        this.authToken = token;

        DatabaseConnection.init(token);
        connection = DatabaseConnection.getInstance();

        try {

            FirebaseResponse response = connection.get("users/" + uid );

            if (response.getSuccess()) {
                Map<String, Object> data = response.getBody();

                this.firstName = (String)data.get("fname");
                this.lastName = (String)data.get("lname");
                this.experience = (int)data.get("experience");
                this.exp = this.experience;
                this.co2Reduction = (double)data.get("co2red");

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
        exp += score;
        this.checkLevel();

        String patchData = String.format("{\"experience\":%s}", experience);
        connection.patch("users/" + uid, patchData);

    }

    /**
     * Increases the attribute co2Reduction.
     * @param red -amount of CO2 reduced.
     */
    public void reduceCo2(double red) throws UnsupportedEncodingException,
            FirebaseException {
        co2Reduction += red;

        String patchData = String.format("{\"co2red\":%s}", co2Reduction);
        connection.patch("users/" + uid, patchData);
    }


    /**
     * Checks current experience to see if the user should level up.
     */
    private void checkLevel() {
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

    public int getExp() {
        return exp;
    }

    public double getCo2Reduction() {
        return co2Reduction;
    }

    public int getExperience() {
        return experience;
    }
}
