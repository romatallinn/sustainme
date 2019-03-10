package view.implementation.javafx;

import controller.SignUpController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import view.interfaces.ISignUpView;

public class JavaFxSignUpView implements ISignUpView {

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

    /**
     * Sends email and password to controller whenever the sign up button is clicked.
     */
    @FXML
    public void signUpAction() {

        if (!pass.getText().equals(repass.getText())) {
            displayStatus("The passwords did not match!");
            return;
        }

        controller.signUpCallback(email.getText(), pass.getText());
    }

    @Override
    public void initView(SignUpController controller) {
        this.controller = controller;
    }

    @Override
    public void displayStatus(String status) {
        statusMsg.setText(status);
        System.out.print(status);
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
