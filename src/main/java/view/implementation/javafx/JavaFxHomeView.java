package view.implementation.javafx;

import controller.HomescreenController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
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
    private Button clothes;
    @FXML
    private Button house;
    @FXML
    private Button transport;
    @FXML
    private Button news;
    @FXML
    private Button score;
    @FXML
    private ProgressBar expBar;


    /**
     * Changes window from home view to sign in view (logout).
     */
    @FXML
    public void goToSignIn() throws IOException {
        controller.logout();
        this.switchScene(logout.getScene(), "signin");
    }

    @FXML
    public void goToFood() throws IOException {
        this.switchScene(food.getScene(), "food");
    }

    @Override
    public void initView(HomescreenController controller) {
        this.controller = controller;
    }

    @Override
    protected void updateLabels() {
        controller.updateViewWithData();
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
