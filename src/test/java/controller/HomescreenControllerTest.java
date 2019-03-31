package controller;

import model.UserProfile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


import supporting.FirebaseAuth;
import view.interfaces.IHomeView;


public class HomescreenControllerTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private IHomeView view;

    private HomescreenController controller;

    @Before
    public void setup() {
        controller = new HomescreenController(view);
    }

    @Test
    public void testUpdateLabels() {

        controller.updateViewWithData();

        verify(view).updateNameLabel(any());
//        verify(view).updateLvlLabel(any());
//        verify(view).updateExpLabel(any());
//        verify(view).updateReducedLabel(any());

    }

//    @Test
//    public void testLogout() {
//        Assert.assertFalse(UserProfile.getInstance().getFirstName().isEmpty());
//        controller.logout();
//        Assert.assertTrue(UserProfile.getInstance().getFirstName().isEmpty());
//
//    }

}
