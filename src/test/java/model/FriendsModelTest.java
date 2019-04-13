package model;

import com.google.gson.JsonObject;
import model.objects.UserData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import server.Application;
import server.supporting.FirebaseConnection;
import supporting.FirebaseAuth;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FriendsModelTest {

    private FriendsModel model;
    private final String testEmail = "test@test.com";
    private final String testPass = "123456Aa";

    @Before
    public void setup() {
        FirebaseConnection.initApp();
        model = new FriendsModel();
        JsonObject jsonObj = FirebaseAuth.getInstance().auth(testEmail, testPass);
        String token = jsonObj.get("idToken").getAsString();
        UserProfile.getInstance().init(testEmail,"5An25iOCs5bisQ2ORzaaoUD9nNo2", token);
    }

    @Test
    public void testGetFriends() {
        List<UserData> one = model.getFriends();
        UserProfile.getInstance().clean();
        JsonObject jsonObj = FirebaseAuth.getInstance().auth("friend@friend.com", "friend");
        String token = jsonObj.get("idToken").getAsString();
        UserProfile.getInstance().init("friend@friend.com","7DFh9M0QWaNhgQkgAdw2fY7LIGk2", token);
        List<UserData> two = model.getFriends();
        assertEquals(one,two);
    }

}
