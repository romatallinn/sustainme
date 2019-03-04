package view.implementation.javafx;

import controller.SignUpController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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

    @FXML
    public void signUpAction() {

        if(!pass.getText().equals(repass.getText())) {
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
    public void show() {

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
