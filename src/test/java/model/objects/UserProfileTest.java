package model.objects;
import org.junit.*;

import static org.junit.Assert.*;

public class UserProfileTest {

    @Test
    public void increaseScoreNoLevelUp() {

        UserProfile user = new UserProfile();
        
        // Increase Exp
        user.setLocalExp(5);

        // Assert Final Stats
        assertEquals(10, user.getExperience());
        assertEquals(2, user.getLevel());

    }

    @Test
    public void increaseScore1LvlUp() {

        UserProfile user = new UserProfile();

        user.setLocalExp(16);

        assertEquals(1, user.getExperience(),0.0);
        assertEquals(3, user.getLevel());

    }

    @Test
    public void testCo2Reduction() {

        UserProfile user = new UserProfile();

        user.setLocalCo2Stats(2.2);
        assertEquals(user.getCo2Reduction(),7.2,0.0);

    }

    @Test
    public void getFirstName() {

        UserProfile user = new UserProfile();
        assertEquals(user.getFirstName(),"Firstname");

    }

    @Test
    public void getLastName() {

        UserProfile user = new UserProfile();
        assertEquals(user.getLastName(),"Lastname");

    }

    @Test
    public void getEmailAddress() {

        UserProfile user = new UserProfile();
        assertEquals(user.getEmailAddress(),"test@test.com");

    }

    @Test
    public void getLevel() {

        UserProfile user = new UserProfile();
        assertEquals(user.getLevel(),2);

    }

    @Test
    public void getExpProgress() {

        UserProfile user = new UserProfile();
        assertEquals(user.getExpProgress(),0.25,0.0);

    }

    @Test
    public void getCo2Reduction() {

        UserProfile user = new UserProfile();
        assertEquals(user.getCo2Reduction(), 5.0, 0);

    }

}