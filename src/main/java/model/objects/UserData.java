package model.objects;

import static java.lang.Math.pow;

public class UserData {

    // Assigned Locally (for master user)
    public String uid = "";
    public String emailAddress = "";
    public int level = 0;

    // Loaded From DB
    public String fname = "";
    public String lname = "";
    public int experience = 0;
    public double co2red = 0;
    public int vegmeals = 0;

    public void calculateLevel() {
        this.level = 1;
        while (this.experience >= 10 * pow(2, this.level - 1)) {

            this.experience -= 10 * pow(2, this.level - 1);
            this.level++;

        }
    }

}
