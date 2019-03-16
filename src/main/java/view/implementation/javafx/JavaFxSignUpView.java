package view.implementation.javafx;

import controller.SignInController;
import controller.SignUpController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import view.interfaces.ILoginView;
import view.interfaces.ISignUpView;

import java.io.IOException;

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
    private Button login;

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

    /**
     * Changes window from sign up view to login view.
     */
    @FXML
    public void login() throws IOException {
        ILoginView view = new JavaFxLoginView();
        SignInController controller = new SignInController(view);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JavaFXLoginView.fxml"));

        view.initView(controller);
        loader.setController(view);

        Scene scene = login.getScene();
        Parent root = loader.load();
        scene.setRoot(root);

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
