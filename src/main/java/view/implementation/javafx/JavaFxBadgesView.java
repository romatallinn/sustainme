package view.implementation.javafx;

import controller.BadgesController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.interfaces.IBadgesView;

import java.io.IOException;

public class JavaFxBadgesView extends JavaFxView implements IBadgesView {

    private BadgesController controller;

    @FXML
    private Button homeBtn;

    @FXML
    private Button treeBtn;

    @FXML
    private ImageView level;

    @FXML
    private ImageView co2;

    @FXML
    private ImageView vegMeal;

    @FXML
    private ImageView bike;

    @FXML
    private ImageView noCar;

    @FXML
    private ImageView pubTrans;

    @Override
    public void initView(BadgesController controller) {
        this.controller = controller;
    }

    @Override
    public void updateLabels() {
        if (controller.getBadge("levelHundredBadge")) {
            level.setImage(new Image("/badges/6.png"));
        }
        if (controller.getBadge("co2ReducedBadge")) {
            co2.setImage(new Image("/badges/4.png"));
        }
        if (controller.getBadge("vegetarianMealBadge")) {
            vegMeal.setImage(new Image("/badges/5.png"));
        }
        if (controller.getBadge("distanceByBikeBadge")) {
            bike.setImage(new Image("/badges/3.png"));
        }
        if (controller.getBadge("kmNoCarUsedBadge")) {
            noCar.setImage(new Image("/badges/2.png"));
        }
        if (controller.getBadge("distancePublicBadge")) {
            pubTrans.setImage(new Image("/badges/7.png"));
        }
    }

    @FXML
    private void goToHome() throws IOException {
        switchScene(homeBtn.getScene(), "home");
    }

    @FXML
    private void goToTree() throws IOException {
        switchScene(homeBtn.getScene(), "fractalTree");
    }
}
