package controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import supporting.FirebaseAuth;
import view.interfaces.ISignInView;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class SignInControllerTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private ISignInView view;

    private SignInController controller;

    @Before
    public void setup() {
        controller = new SignInController(view);
    }

    @Test
    public void testSignInSuccess() throws IOException {
        controller.signInCallback("test@test.com", "123456Aa");
        verify(view).goToHome();
    }

    @Test
    public void testSignInPasswordFailure() {
        controller.signInCallback("test@test.com", "123");
        verify(view).displayStatus(any());
    }

    @Test
    public void testSignInEmailFailure() {
        controller.signInCallback("invalid@email.com", "123");
        verify(view).displayStatus(any());
    }


}
