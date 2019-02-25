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


    UserProfile(String first, String last, String email){
        firstName = first;
        lastName = last;
        emailAddress = email;
        level = 1;
        experience = 0;
    }

    void IncreaseScore(int score){
        experience += score;
        this.CheckLevel();
    }

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
