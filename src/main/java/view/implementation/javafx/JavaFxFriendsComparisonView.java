package view.implementation.javafx;

import controller.FriendsComparisonController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import model.UserProfile;
import model.objects.UserData;
import view.interfaces.IFriendsComparisonView;

import java.io.IOException;
import java.text.DecimalFormat;


public class JavaFxFriendsComparisonView extends JavaFxView implements IFriendsComparisonView {

    private FriendsComparisonController controller;

    @FXML
    private Button homeBtn;


    // Left: local user

    @FXML
    private Circle profilePic;

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
    private Circle friendProfilePic;

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
    protected void updateLabels() {
        Image im = new Image("https://upload.wikimedia.org/wikipedia/commons/8/89/Portrait_Placeholder.png", false);
        profilePic.setFill(new ImagePattern(im));
        friendProfilePic.setFill(new ImagePattern(im));
    }


    @Override
    public void updateFriendsData(UserData friend) {

        friend.calculateLevel();

        friendName.setText(friend.fname + " " + friend.lname);
        friendLvl.setText("Level: " + friend.level);
        friendExpBar.setProgress(friend.experience);

        String str = new DecimalFormat("#.##").format(friend.co2red);
        friendCO2.setText(str);

    }

    @Override
    public void updateLocalUserData() {

        UserProfile profile = UserProfile.getInstance();

        name.setText(profile.getFirstName() + " " + profile.getLastName());
        level.setText("Level: " + profile.getLevel());
        expBar.setProgress(profile.getExpProgress());

        String str = new DecimalFormat("#.##").format(profile.getCo2Reduction());
        myCO2.setText(str);

    }
}
