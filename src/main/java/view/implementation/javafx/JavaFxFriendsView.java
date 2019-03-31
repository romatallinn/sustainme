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

    @Override
    public void initView(FriendsController controller) {
        this.controller = controller;
    }

    @Override
    public void updateFriendsList(List<UserData> friends) {
        this.friends = friends;
        // TODO: update grid/table/scrollpane with passed data.
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
