package controller;

import model.objects.UserProfile;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import view.interfaces.IFriendView;

public class FriendsControllerTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private IFriendView view;

    private FriendsController controller;

    @Before
    public void setup() {
        UserProfile.getInstance().clean();
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
        Mockito.verify(view).displayStatus(Mockito.anyString());
    }

    @Test
    public void testFriendAddSuccess() {
        controller.addFriendByEmail("test@test.com");
        Mockito.verify(view).displayStatus(Mockito.anyString());
    }







}
