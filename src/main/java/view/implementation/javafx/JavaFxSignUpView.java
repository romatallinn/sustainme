package view.implementation.javafx;

import controller.SignUpController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import view.interfaces.ISignUpView;

import java.io.IOException;

public class JavaFxSignUpView extends JavaFxView implements ISignUpView {

    private SignUpController controller;

    @FXML
    private TextField name;
    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    private TextField pass;
    @FXML
    private TextField repass;
    @FXML
    private Text statusMsg;
    @FXML
    private Button login;

    /**
     * Sends email and password to controller whenever the sign up button is clicked.
     */
    @FXML
    public void signUpAction() {

        controller.signUpCallback(email.getText(), pass.getText(), repass.getText(),
                name.getText(), lastname.getText());
    }

    /**
     * Changes window from sign up view to goToSignin view.
     */
    @FXML
    public void goToSignIn() throws IOException {
        this.switchScene(login.getScene(), "signin");
    }

    public void goToHome() throws IOException {
        this.switchScene(login.getScene(), "home");
    }

    @Override
    public void initView(SignUpController controller) {
        this.controller = controller;
    }

    @Override
    public void displayStatus(String status) {
        statusMsg.setText(status);
        System.out.println(status);
    }

    @Override
    public void clearSignUpFields() {
        name.clear();
        lastname.clear();
        email.clear();
        pass.clear();
        repass.clear();
    }
}
