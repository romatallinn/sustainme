package controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import view.interfaces.ISignUpView;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

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
    public void testSignUpCallbackExist() throws IOException {
        controller.signUpCallback("test@test.com", "123456Aa", "John", "Smith");
        verify(view).displayStatus(any());
    }

    @Test
    public void testSignUpCallbackWrongEmailFormat() throws IOException {
        controller.signUpCallback("awd", "123456Aa", "John", "Smith");
        verify(view).displayStatus(any());
    }

    @Test
    public void testSignUpCallbackMissingEmail() throws IOException {
        controller.signUpCallback("", "123456Aa", "John", "Smith");
        verify(view).displayStatus(any());
    }

}
