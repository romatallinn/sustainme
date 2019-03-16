package view.implementation.javafx;

import controller.SignInController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import view.interfaces.ILoginView;

import java.io.IOException;

public class JavaFxSignInView extends JavaFXView implements ILoginView {

    private SignInController controller;

    @FXML
    private Button login;
    @FXML
    private Text statusMsg;
    @FXML
    private TextField email;
    @FXML
    private TextField pass;


    /**
     * Changes window from goToSignin view to sign up view.
     */
    @FXML
    public void goToSignUp() throws IOException {
        this.switchScene(login.getScene(), "signup");
    }

    @Override
    public void initView(SignInController controller) {
        this.controller = controller;
    }

    @Override
    public void displayStatus(String status) {
        statusMsg.setText(status);
        System.out.print(status);
    }

    @Override
    public void clearSignUpFields() {
        email.clear();
        pass.clear();
    }
}
