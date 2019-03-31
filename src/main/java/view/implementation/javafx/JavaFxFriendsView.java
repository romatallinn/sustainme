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

<<<<<<< Updated upstream
    private List<UserData> friends;
=======
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

>>>>>>> Stashed changes

    @Override
    public void initView(FriendsController controller) {
        this.controller = controller;
    }

    @Override
    public void updateFriendsList(List<UserData> friends) {
<<<<<<< Updated upstream
        this.friends = friends;
        // TODO: update grid/table/scrollpane with passed data.
=======
        if (friend1 == null) {
            friend1.setText(addFriendEmail.getText());
        }
        if (friend2 == null) {
            friend2.setText(addFriendEmail.getText());
        }
        if (friend3 == null) {
            friend3.setText(addFriendEmail.getText());
        }
        if (friend4 == null) {
            friend4.setText(addFriendEmail.getText());
        }
        if (friend5 == null) {
            friend5.setText(addFriendEmail.getText());
        }
>>>>>>> Stashed changes
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
