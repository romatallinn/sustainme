package view.implementation.javafx;

import controller.FriendsController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.objects.UserData;
import view.interfaces.IFriendView;

import java.io.IOException;
import java.util.List;


public class JavaFxFriendsView extends JavaFxView implements IFriendView {

    private FriendsController controller;

    @FXML
    private Button homeBtn;

    @FXML
    private TextField addFriendEmail;


    @Override
    public void initView(FriendsController controller) {
        this.controller = controller;
    }

    @Override
    public void updateFriendsList(List<UserData> friends) {
        // TODO: update grid/table/scrollpane with passed data.
    }

    @FXML
    private void goToHome() throws IOException {
        switchScene(homeBtn.getScene(), "home");
    }

    @FXML
    private void addFriendsAction() {
        controller.addFriendByEmail(addFriendEmail.getText());
        addFriendEmail.clear();
    }


}
