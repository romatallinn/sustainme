package model;



import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import server.Application;
import server.supporting.FirebaseConnection;
import supporting.FirebaseAuth;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TransportModelTest {

    private TransportModel model;
    private final String testEmail = "test@test.com";
    private final String testPass = "123456Aa";

    @Before
    public void setup() {
        FirebaseConnection.initApp();
        model = new TransportModel();
        JsonObject jsonObj = FirebaseAuth.getInstance().auth(testEmail, testPass);
        String token = jsonObj.get("idToken").getAsString();
        UserProfile.getInstance().init(testEmail,"5An25iOCs5bisQ2ORzaaoUD9nNo2", token);
    }

    @Test
    public void testGetters() {
        JsonObject jsonObj = FirebaseAuth.getInstance().auth("stat@test.com", "letsgo");
        String token = jsonObj.get("idToken").getAsString();
        UserProfile.getInstance().init("stat@test.com","rGYZg8W9IYOjvYwYvdFsvqUuruZ2", token);
        assertEquals(0, model.getBikeDistance());
        assertEquals(0, model.getPublicDistance());
    }

    @Test
    public void testAddDistanceCycled() {
        int before = model.getBikeDistance();
        model.addDistanceCycled(20);
        int after = model.getBikeDistance();
        assertEquals(20,after - before);
    }

    @Test
    public void testEmptyTrainTravel(){
        UserProfile.getInstance().clean();
        model.addTrainDistanceTraveled(10);
        assertEquals(0,model.getPublicDistance());
    }

    @Test
    public void testAddTrainDistanceTraveled() {
        int before = model.getPublicDistance();
        model.addTrainDistanceTraveled(10);
        int after = model.getPublicDistance();
        assertEquals(10,after - before);
    }

    @Test
    public void testEmptyBusTravel(){
        UserProfile.getInstance().clean();
        model.addBusDistanceTraveled(10);
        assertEquals(0,model.getPublicDistance());
    }

    @Test
    public void testAddBusDistanceTraveled() {
        int before = model.getPublicDistance();
        model.addBusDistanceTraveled(10);
        int after = model.getPublicDistance();
        assertEquals(10,after - before);
    }
}
