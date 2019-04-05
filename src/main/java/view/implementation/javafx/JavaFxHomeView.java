package view.implementation.javafx;

import controller.HomescreenController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import view.interfaces.IHomeView;

import java.io.IOException;

public class JavaFxHomeView extends JavaFxView implements IHomeView {

    private HomescreenController controller;

    @FXML
    private Button logout;
    @FXML
    private Text name;
    @FXML
    private Text level;
    @FXML
    private Button food;
    @FXML
    private ProgressBar expBar;
    @FXML
    private Circle profilePic;


    /**
     * Changes window from home view to sign in view (logout).
     */
    @FXML
    public void invokeLogout() throws IOException {
        controller.logout();
        this.switchScene(logout.getScene(), "signin");
    }

    @FXML
    public void goToFood() throws IOException {
        this.switchScene(food.getScene(), "food");
    }

    @FXML
    public void goToTransport() throws IOException {
        this.switchScene(food.getScene(), "transport");
    }

    @FXML
    public void goToEnergy() throws IOException {
        this.switchScene(food.getScene(), "energy");
    }

    @FXML
    public void goToFriends() throws IOException {
        this.switchScene(food.getScene(), "friends");
    }

    @FXML
    public void goToRecycling() throws IOException {
        this.switchScene(food.getScene(), "recycling");
    }

    @Override
    public void initView(HomescreenController controller) {
        this.controller = controller;
    }

    @Override
    protected void updateLabels() {
        controller.updateViewWithData();
        Image im = new Image("https://upload.wikimedia.org/wikipedia/commons/8/89/Portrait_Placeholder.png", false);
        profilePic.setFill(new ImagePattern(im));
    }

    @Override
    public void updateNameLabel(String name) {
        this.name.setText(name);
    }

    @Override
    public void updateExpLabel(double expProgress) {
        this.expBar.setProgress(expProgress);
    }

    @Override
    public void updateLvlLabel(int lvl) {
        this.level.setText("Level: " + lvl);
    }

    @Override
    public void updateReducedLabel(double redCO2) {
        // Update reduced CO2
    }
}
