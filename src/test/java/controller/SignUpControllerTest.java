package controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import server.Application;
import view.interfaces.ISignUpView;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SignUpControllerTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private ISignUpView view;

    private SignUpController controller;

    @Before
    public void setup() {
        controller = new SignUpController(view);
    }

    @Test
    public void testSignUpCallbackExist() {
        controller.signUpCallback("test@test.com", "123456Aa", "123456Aa", "John", "Smith");
        verify(view).displayStatus("Email already in use");
    }

    @Test
    public void testSignUpCallbackWrongEmailFormat() {
        controller.signUpCallback("awd", "123456Aa", "123456Aa", "John", "Smith");
        verify(view).displayStatus("Invalid email address");
    }

    @Test
    public void testSignUpCallbackMissingEmail() {
        controller.signUpCallback("", "123456Aa", "123456Aa", "John", "Smith");
        verify(view).displayStatus("MISSING_EMAIL");
    }

    @Test
    public void testSignUpCallback2PassWrong() {
        controller.signUpCallback("test@test.com", "123456Aa", "123456AA", "John", "Smith");
        verify(view).displayStatus("Passwords do not match!");
    }


    @Test
    public void testSignUpCallback() throws IOException {
        controller.signUpCallback("delete@user.com", "123456Aa", "123456Aa", "Delete", "User");
        verify(view).goToHome();
    }

}
