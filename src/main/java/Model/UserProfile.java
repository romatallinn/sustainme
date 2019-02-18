package Model;

import static java.lang.Math.pow;

/**
 *
 */
public class UserProfile {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private int level;
    private int experience;


    public UserProfile(String first, String last, String email){
        firstName = first;
        lastName = last;
        emailAddress = email;
        level = 1;
        experience = 0;
    }

    public void IncreaseScore(int score){
        experience += score;
        this.CheckLevel();
    }
    public void CheckLevel(){
        while (experience >= 10*pow(2,level-1)) {
            level++;
            experience -= 10 * pow(2, level - 1);
        }
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmailAddress(){
        return emailAddress;
    }
    public int getLevel(){
        return level;
    }
    public int getExperience(){
        return experience;
    }

}
