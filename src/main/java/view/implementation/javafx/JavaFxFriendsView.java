package view.implementation.javafx;

import controller.FriendsController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.objects.UserData;
import view.interfaces.IFriendView;
import view.interfaces.IFriendsComparisonView;

import java.io.IOException;
import java.util.List;


public class JavaFxFriendsView extends JavaFxView implements IFriendView {

    private FriendsController controller;

    @FXML
    private Button homeBtn;

    @FXML
    private TextField addFriendEmail;

    private List<UserData> friends;

    @FXML
    private Button friend1;
    @FXML
    private Button friend2;
    @FXML
    private Button friend3;
    @FXML
    private Button friend4;
    @FXML
    private Button friend5;

    @Override
    public void initView(FriendsController controller) {
        this.controller = controller;
    }

    @Override
    public void updateFriendsList(List<UserData> friends) {
        this.friends = friends;
        if (friends.get(0) != null) {
            friend1.setText(friends.get(0).fname);
        }
        if (friends.get(1) != null) {
            friend2.setText(friends.get(1).fname);
        }
        if (friends.get(2) != null) {
            friend3.setText(friends.get(2).fname);
        }
        if (friends.get(3) != null) {
            friend4.setText(friends.get(3).fname);
        }
    }

    @Override
    protected void updateLabels() {
        controller.updateViewWithData();
    }

    @Override
    public void displayStatus(String msg) {
        System.out.println(msg);
    }

    @FXML
    private void goToHome() throws IOException {

        // switchScene(homeBtn.getScene(), "home");

        if (friends.size() == 0) {
            return;
        }

        switchScene(homeBtn.getScene(), "friendsComparison");

        SceneFx scene = JavaFxApplication.scenes.get("friendsComparison");
        IFriendsComparisonView view = (IFriendsComparisonView)scene.getView();
        view.updateFriendsData(friends.get(0));
        view.updateLocalUserData();

    }

    @FXML
    private void addFriendsAction() throws IOException {
        controller.addFriendByEmail(addFriendEmail.getText());
        addFriendEmail.clear();
    }


}
