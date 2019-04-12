package view.implementation.javafx;

import controller.HomescreenController;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import model.FractalTreeModel;
import model.UserProfile;
import model.objects.FractalTreeResponse;
import view.element.FractalTree;
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
    @FXML
    private Text myCO2;
    @FXML
    private Canvas canvas;


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
    
    public void goToTree() throws IOException {
        this.switchScene(food.getScene(), "fractalTree");
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

        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Co2 data per feature out of the database
        String firstName = UserProfile.getInstance().getFirstName();
        FractalTreeResponse result = new FractalTreeModel().fractalTreeGetData();
        double bikeCO2 = result.getBikeCo2();
        double vegmealsCO2 = result.getVegmealsCO2();
        double localproduceCO2 = result.getLocalproduceCO2();
        double publicCO2 = result.getPublicCO2();
        // TODO: temp, sol, paper, plastic


        // Scores: bike-fuchsia, localProduce-blueviolet, publicTransport-azure,
        // veggie-lime, temperature-darkorange, solarPanels-red, paper-yellow, plastic-gray
        FractalTree ft = new FractalTree(
                firstName,
                new double[]{bikeCO2, localproduceCO2, publicCO2, vegmealsCO2, 450, 500, 889, 909},
                new Color[]{
                    Color.FUCHSIA,
                    Color.BLUEVIOLET,
                    Color.AZURE,
                    Color.LIME,
                    Color.DARKORANGE,
                    Color.RED,
                    Color.YELLOW,
                    Color.GRAY
                }
        );
        ft.drawTree(canvas);
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
        this.myCO2.setText(redCO2 + " kg");
    }
}
