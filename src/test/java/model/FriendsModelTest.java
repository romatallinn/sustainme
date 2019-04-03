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

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FriendsModelTest {

    private FriendsModel model;

    @Before
    public void setup() throws InterruptedException {
        model = new FriendsModel();
        TimeUnit.SECONDS.sleep(5);
        UserProfile.getInstance().init("none","dynamicTestUser", "dynamicTestUser");
    }

    @Test
    public void testAddFriend() {
        model.addFriendByEmail("test@test.com");
    }

}
