package view.implementation.javafx;

import controller.FriendsController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
    private VBox vboxButtons;

    @FXML
    private Text statusMsg;

    @FXML
    private Text counterStatus;

    @Override
    public void initView(FriendsController controller) {
        this.controller = controller;
    }

    @Override
    public void updateFriendsList(List<UserData> friends) {
        this.friends = friends;
        vboxButtons.getChildren().clear();
        for(int i = 0; i < friends.size(); i++) {
            Button btnNumber = new Button();
            btnNumber.setCursor(Cursor.HAND);
            btnNumber.setText(friends.get(i).fname + " " + friends.get(i).lname);
            int finalI = i;
            btnNumber.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    goToComparison(friends.get(finalI));
                }
            });
            vboxButtons.getChildren().add(btnNumber);
        }

    }

    @Override
    protected void updateLabels() {
        controller.updateViewWithData();
    }

    @Override
    public void displayStatus(String msg) {
        statusMsg.setText(msg);
        counterStatus.setText(msg);
        System.out.println(msg);
    }

    private void goToComparison(UserData friend) {

        try {
            switchScene(homeBtn.getScene(), "friendsComparison");
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }

        SceneFx scene = JavaFxApplication.scenes.get("friendsComparison");
        IFriendsComparisonView view = (IFriendsComparisonView)scene.getView();
        view.updateFriendsData(friend);
        view.updateLocalUserData();
    }

    @FXML
    private void goToHome() throws IOException {
        switchScene(homeBtn.getScene(), "home");
    }

    @FXML
    private void addFriendsAction() {
        controller.addFriendByEmail(addFriendEmail.getText());
        addFriendEmail.clear();
        controller.updateViewWithData();
    }


}
