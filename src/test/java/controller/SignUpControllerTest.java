package controller;

import org.junit.Before;
import view.implementation.javafx.JavaFxSignUpView;
import view.interfaces.ISignUpView;

public class SignUpControllerTest {

    private SignUpController controller;

    @Before
    public void init() {
        ISignUpView view = new JavaFxSignUpView();
        controller = new SignUpController(view);
        view.initView(controller);
    }

}
