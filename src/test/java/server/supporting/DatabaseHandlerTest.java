package server.supporting;

import com.google.firebase.database.DatabaseReference;
import model.objects.UserData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DatabaseHandlerTest {

    private final String uid = "5An25iOCs5bisQ2ORzaaoUD9nNo2";


    @Before
    public void setup() {
        FirebaseConnection.initApp();
     }

    @Test
    public void testGetUserData() throws InterruptedException {

        String fname = "Firstname";
        String lname = "Lastname";

        UserData data = DatabaseHandler.getUserData(uid);

        Assert.assertEquals(data.uid, uid);
        Assert.assertEquals(data.fname, fname);
        Assert.assertEquals(data.lname, lname);

    }

    @Test
    public void testRetrieveVegMealsCount() throws InterruptedException {

        int count = 3;

        int testCount = DatabaseHandler.retrieveFeatureCounter(uid, "vegmeals");

        Assert.assertEquals(count, testCount);

    }

    @Test
    public void testFeatureCounterIncrease() throws InterruptedException {

        String feature = "test";

        int initial = DatabaseHandler.retrieveFeatureCounter(uid, feature);

        DatabaseHandler.increaseFeatureCounter(uid, feature, 2);
        Thread.sleep(3000);

        int end = DatabaseHandler.retrieveFeatureCounter(uid, feature);

        Assert.assertEquals(initial, end-2);


    }

    @Test
    public void testUserInit() throws InterruptedException {

        String fname = "Testname";
        String lname = "Lastname";
        String uid = "testid";

        DatabaseHandler.initUser(uid, fname, lname);
        UserData data = DatabaseHandler.getUserData(uid);

        Assert.assertEquals(uid, data.uid);
        Assert.assertEquals(fname, data.fname);
        Assert.assertEquals(lname, data.lname);

    }

}
