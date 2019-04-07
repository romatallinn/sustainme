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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)

public class RecyclingTest {

    private final String testEmail = "test@test.com";
    private final String testPass = "123456Aa";
    private RecyclingModel model;

    @Before
    public void setup() {
        FirebaseConnection.initApp();
        model = new RecyclingModel();
        JsonObject jsonObj = FirebaseAuth.getInstance().auth(testEmail, testPass);
        String token = jsonObj.get("idToken").getAsString();
        UserProfile.getInstance().init(testEmail,"5An25iOCs5bisQ2ORzaaoUD9nNo2", token);
    }

    @Test
    public void testGetPaperRecyclingCount() {
        JsonObject jsonObj = FirebaseAuth.getInstance().auth("stat@test.com", "letsgo");
        String token = jsonObj.get("idToken").getAsString();
        UserProfile.getInstance().init("stat@test.com","rGYZg8W9IYOjvYwYvdFsvqUuruZ2", token);
        Assert.assertEquals(0, model.getPaperRecyclingCount(), 0);
    }

    @Test
    public void testEmptyAddAmountPaperRecycling(){
        UserProfile.getInstance().clean();
        model.addAmountPaperRecycling(1);
        assertEquals(0,model.getPaperRecyclingCount(),0.0);
    }

    @Test
    public void testAddAmountPaperRecycling() {
        double before = model.getPaperRecyclingCount();
        int expBef = UserProfile.getInstance().getExperience()
            + (int) (10 * Math.pow(2,(UserProfile.getInstance().getLevel() - 1)));
        model.addAmountPaperRecycling(5);
        double after = model.getPaperRecyclingCount();
        int expAft = UserProfile.getInstance().getExperience()
            + (int) (10 * Math.pow(2,(UserProfile.getInstance().getLevel() - 1)));
        assertEquals(5,after - before,0.0);
        assertEquals(5, expAft - expBef);
    }
}
