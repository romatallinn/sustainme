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

public class JavaFxLoginView implements ILoginView {

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
     * Changes window from login view to sign up view.
     */
    @FXML
    public void signUp() throws IOException {
        ISignUpView view = new JavaFxSignUpView();
        SignUpController controller = new SignUpController(view);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JavaFXSignUpView.fxml"));

        view.initView(controller);
        loader.setController(view);

        Scene scene = login.getScene();
        Parent root = loader.load();
        scene.setRoot(root);
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
