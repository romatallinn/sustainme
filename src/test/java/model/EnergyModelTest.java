package model;

import com.google.gson.JsonObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import server.Application;
import server.supporting.FirebaseConnection;
import supporting.FirebaseAuth;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
public class EnergyModelTest {

    private final String testEmail = "test@test.com";
    private final String testPass = "123456Aa";
    private EnergyModel model;

    @Before
    public void setup() {
        FirebaseConnection.initApp();
        model = new EnergyModel();
        JsonObject jsonObj = FirebaseAuth.getInstance().auth(testEmail, testPass);
        String token = jsonObj.get("idToken").getAsString();
        UserProfile.getInstance().init(testEmail,"5An25iOCs5bisQ2ORzaaoUD9nNo2", token);
    }

    @Test
    public void testGetters() {
        JsonObject jsonObj = FirebaseAuth.getInstance().auth("stat@test.com", "letsgo");
        String token = jsonObj.get("idToken").getAsString();
        UserProfile.getInstance().init("stat@test.com","rGYZg8W9IYOjvYwYvdFsvqUuruZ2", token);
        Assert.assertEquals(0, model.getSolarArea());
        Assert.assertEquals(21, model.getHomeTemperature(), 0);
    }

    @Test
    public void testEmptyGetters(){
        UserProfile.getInstance().clean();
        Assert.assertEquals(21, model.getHomeTemperature(), 0);
        Assert.assertEquals(0, model.getSolarArea());
    }

    @Test
    public void testEmptySolarArea(){
        UserProfile.getInstance().clean();
        model.addSolarArea(2);
        assertEquals(0,model.getSolarArea());
    }

    @Test
    public void testAddSolarArea() {
        int before = model.getSolarArea();
        int expBef = UserProfile.getInstance().getExperience()
                + (int) (10 * (Math.pow(2,(UserProfile.getInstance().getLevel()))-1));
        model.addSolarArea(2);
        int after = model.getSolarArea();
        int expAft = UserProfile.getInstance().getExperience()
                + (int) (10 * (Math.pow(2,(UserProfile.getInstance().getLevel()))-1));
        assertEquals(2,after - before);
        assertEquals(20, expAft - expBef);
    }

    @Test
    public void testEmptyTemperature(){
        UserProfile.getInstance().clean();
        model.lowerTemperature(17);
        assertEquals(21,model.getHomeTemperature(),0.0);
    }

    @Test
    public void testLowerTemperature() {
        model.lowerTemperature(21);
        double before = model.getHomeTemperature();
        int expBef = UserProfile.getInstance().getExperience()
                + (int) (10 * (Math.pow(2,(UserProfile.getInstance().getLevel()))-1));
        model.lowerTemperature(18);
        double after = model.getHomeTemperature();
        int expAft = UserProfile.getInstance().getExperience()
                + (int) (10 * (Math.pow(2,(UserProfile.getInstance().getLevel()))-1));
        assertEquals(3,before - after,0.0);
        assertEquals(0, expAft - expBef);
    }


}
