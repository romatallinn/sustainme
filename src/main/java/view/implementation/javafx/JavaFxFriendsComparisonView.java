package view.implementation.javafx;

import controller.FriendsComparisonController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import model.objects.UserData;
import model.objects.UserProfile;
import view.interfaces.IFriendsComparisonView;

import java.io.IOException;


public class JavaFxFriendsComparisonView extends JavaFxView implements IFriendsComparisonView {

    private FriendsComparisonController controller;

    @FXML
    private Button homeBtn;


    // Left: local user

    @FXML
    private Text name;

    @FXML
    private Text level;

    @FXML
    private ProgressBar expBar;

    @FXML
    private Text myCO2;


    // Right: friends

    @FXML
    private Text friendName;

    @FXML
    private Text friendLvl;

    @FXML
    private ProgressBar friendExpBar;

    @FXML
    private Text friendCO2;



    @Override
    public void initView(FriendsComparisonController controller) {
        this.controller = controller;
    }

    @FXML
    private void goToBack() throws IOException {
        switchScene(homeBtn.getScene(), "friends");
    }


    @Override
    public void updateFriendsData(UserData friend) {

        friendName.setText(friend.fname + " " + friend.lname);
        friendLvl.setText("Level: " + friend.level);
        friendExpBar.setProgress(friend.experience);

        friendCO2.setText(Double.toString(friend.co2red));
    }

    @Override
    public void updateLocalUserData() {

        UserProfile profile = UserProfile.getInstance();

        name.setText(profile.getFirstName() + " " + profile.getLastName());
        level.setText("Level: " + profile.getLevel());
        expBar.setProgress(profile.getExpProgress());

        myCO2.setText(Double.toString(profile.getCo2Reduction()));

    }
}
