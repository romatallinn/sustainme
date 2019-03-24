package server.supporting;

import model.objects.UserData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DatabaseHandlerTest {

    private final String staticUid = "staticTestUser";
    private final String dynamicUid = "dynamicTestUser";



    @Before
    public void setup() {
        FirebaseConnection.initApp();
     }

    @Test
    public void testGetUserData() throws InterruptedException {

        String fname = "Static";
        String lname = "Testuser";

        UserData data = DatabaseHandler.getUserData(staticUid);

        Assert.assertEquals(data.uid, staticUid);
        Assert.assertEquals(data.fname, fname);
        Assert.assertEquals(data.lname, lname);

    }

    @Test
    public void testRetrieveVegMealsCount() throws InterruptedException {

        int count = 4;

        int testCount = DatabaseHandler.retrieveFeatureCounter(staticUid, "vegmeals");

        Assert.assertEquals(count, testCount);

    }

    @Test
    public void testExpIncrease() throws InterruptedException {

        UserData initial = DatabaseHandler.getUserData(dynamicUid);

        int newVal = DatabaseHandler.increaseExpBy(dynamicUid, 5);

        Assert.assertEquals(initial.experience, newVal-5);


    }

    @Test
    public void testCo2Increase() throws InterruptedException {

        UserData initial = DatabaseHandler.getUserData(dynamicUid);

        double newVal = DatabaseHandler.increaseCO2RedBy(dynamicUid, 5.0);

        Assert.assertEquals(initial.co2red, newVal-5, 0.1);


    }

    @Test
    public void testUserInit() throws InterruptedException {

        String fname = "Static";
        String lname = "Testuser";
        String uid = "initTestUser";

        DatabaseHandler.initUser(uid, fname, lname);
        UserData data = DatabaseHandler.getUserData(uid);

        Assert.assertEquals(uid, data.uid);
        Assert.assertEquals(fname, data.fname);
        Assert.assertEquals(lname, data.lname);

    }

}
