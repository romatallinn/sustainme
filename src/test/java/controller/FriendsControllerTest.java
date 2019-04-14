package controller;

import com.google.gson.JsonObject;
import model.UserProfile;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import server.Application;
import server.supporting.FirebaseConnection;
import supporting.FirebaseAuth;
import view.interfaces.IFriendView;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FriendsControllerTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private IFriendView view;

    private FriendsController controller;

    @Before
    public void setup() {
        UserProfile.getInstance().clean();
        FirebaseConnection.initApp();
        controller = new FriendsController(view);
    }

    @Test
    public void testUpdate() {
        controller.updateViewWithData();
        Mockito.verify(view).updateFriendsList(Mockito.any());
    }

    @Test
    public void testFriendAddFailure() {
        controller.addFriendByEmail("fff");
        Mockito.verify(view).displayStatus("Friend not found");
    }

    @Test
    public void testFriendAddSuccess() {
        JsonObject jsonObj = FirebaseAuth.getInstance().auth("test@test.com", "123456Aa");
        String token = jsonObj.get("idToken").getAsString();
        UserProfile.getInstance().init("test@test.com","5An25iOCs5bisQ2ORzaaoUD9nNo2", token);
        controller.addFriendByEmail("stat@test.com");
        Mockito.verify(view).displayStatus("Friend was added!");
    }







}
