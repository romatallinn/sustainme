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

    /**
     * Constructor for the UserProfile class, defaults level to 1, and experience to 0.
     * @param first - first name of the user.
     * @param last - last name of the user.
     * @param email - emailt of the user.
     */
    public UserProfile(String first, String last, String email) {
        firstName = first;
        lastName = last;
        emailAddress = email;
        level = 1;
        experience = 0;
    }

    /**
     * Increases score, calls CheckLevel.
     * @param score - the amount on which the experience needs is to be increased.
     */
    public void increaseScore(int score) {
        experience += score;
        this.checkLevel();
    }

    /**
     * Checks current experience to see if the user shouldlevel up.
     */
    private void checkLevel() {
        while ( experience >= 10 * pow(2, level - 1) ) {
            experience -= 10 * pow(2, level - 1);
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

    public int getExperience() {
        return experience;
    }

}
