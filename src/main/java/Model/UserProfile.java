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

    /**
     * Constructor for the UserProfile class, defaults level to 1, and experience to 0
     * @param first
     * @param last
     * @param email
     */
    UserProfile(String first, String last, String email){
        firstName = first;
        lastName = last;
        emailAddress = email;
        level = 1;
        experience = 0;
    }

    /**
     * Increases score, calls CheckLevel
     * @param score
     */
    void IncreaseScore(int score){
        experience += score;
        this.CheckLevel();
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

}
