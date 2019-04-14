package model;

import org.junit.*;

import static org.junit.Assert.*;

public class UserProfileTest {

    @Test
    public void testExpSetter() {

        UserProfile user = new UserProfile();
        
        // Increase Exp
        user.setLocalExp(5);

        // Assert Final Stats
        assertEquals(5, user.getExperience());

    }


    @Test
    public void testCo2Setter() {

        UserProfile user = new UserProfile();

        user.setLocalCo2Stats(2.2);

        assertEquals(2.2, user.getCo2Reduction(),0.0);

    }

    @Test
    public void getFirstName() {

        UserProfile user = new UserProfile();
        assertEquals(user.getFirstName(),"Firstname");

    }

    @Test
    public void getLastName() {

        UserProfile user = new UserProfile();
        assertEquals("Lastname", user.getLastName());

    }

    @Test
    public void getEmailAddress() {

        UserProfile user = new UserProfile();
        assertEquals("test@test.com", user.getEmailAddress());

    }

    @Test
    public void getLevel() {

        UserProfile user = new UserProfile();
        assertEquals(2, user.getLevel());

    }

    @Test
    public void getExpProgress() {

        UserProfile user = new UserProfile();
        assertEquals(0.25, user.getExpProgress(),0.0);

    }

    @Test
    public void getCo2Reduction() {

        UserProfile user = new UserProfile();
        assertEquals(user.getCo2Reduction(), 5.0, 0);

    }

    @Test
    public void cleanTest() {
        UserProfile user = new UserProfile();

        user.setLocalExp(5);
        assertEquals(0.5, user.getExpProgress(), 0);
        user.clean();
        assertEquals(0, user.getExpProgress(), 0);
    }

    @Test
    public void getUidTest() {
        UserProfile user = new UserProfile();

        assertEquals("", user.getUid());
    }

    @Test
    public void checkCO2RedBadge() {
        UserProfile user = new UserProfile();

        Assert.assertFalse(user.checkBadgeCo2Reduction());
        user.setLocalCo2Stats(101);
        Assert.assertTrue(user.checkBadgeCo2Reduction());

    }

    @Test
    public void checkLvlHundredBadge() {
        UserProfile user = new UserProfile();

        Assert.assertFalse(user.checkBadgeLevelHundred());
        user.setLocalExp(1000000000);
        System.out.println(user.getLevel());
        Assert.assertTrue(user.checkBadgeLevelHundred());

    }

    @Test
    public void logoutTest() {
        UserProfile user = new UserProfile();

        user.setLocalExp(5);
        user.logout();
        assertEquals(0, user.getExpProgress(), 0);
    }

}