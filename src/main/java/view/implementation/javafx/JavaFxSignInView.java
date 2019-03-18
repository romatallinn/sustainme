package view.implementation.javafx;

import controller.SignInController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import view.interfaces.ISignInView;

import java.io.IOException;

public class JavaFxSignInView extends JavaFxView implements ISignInView {

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

    @FXML
    private void signIn() {
        controller.signInCallback(email.getText(), pass.getText());
    }

    @Override
    public void goToHome() throws IOException {
        this.switchScene(login.getScene(), "home");
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

}
