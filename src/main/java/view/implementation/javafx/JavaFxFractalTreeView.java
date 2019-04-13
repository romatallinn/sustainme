package view.implementation.javafx;


import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import model.FractalTreeModel;
import model.UserProfile;

import model.objects.FractalTreeResponse;
import view.element.FractalTree;

import java.io.IOException;


public class JavaFxFractalTreeView extends JavaFxView {

    @FXML
    private Canvas canvas;

    @FXML
    private Button homeBtn;

    @FXML
    private void goToHome() throws IOException {
        switchScene(homeBtn.getScene(), "home");
    }

    @FXML
    private void goToBadges() throws IOException {
        switchScene(homeBtn.getScene(), "badges");
    }

    @Override
    public void updateLabels() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Co2 data per feature out of the database
        String firstName = UserProfile.getInstance().getFirstName();
        FractalTreeResponse result = new FractalTreeModel().fractalTreeGetData();
        double bikeCO2 = result.getBikeCo2();
        double vegmealsCO2 = result.getVegmealsCO2();
        double localproduceCO2 = result.getLocalproduceCO2();
        double publicCO2 = result.getPublicCO2();
        double plasticrecyclingCO2 = result.getPlasticrecyclingCO2();
        double paperrecyclingCO2 = result.getPaperrecyclingCO2();
        double solarareaCO2 = result.getSolarareaCO2();
        double temperatureCO2 = result.getTemperatureCO2();


        // Scores: bike-fuchsia, localProduce-blueviolet, publicTransport-azure,
        // veggie-lime, temperature-darkorange, solarPanels-red, paper-yellow, plastic-gray
        FractalTree ft = new FractalTree(
                firstName,
                new double[]{bikeCO2, localproduceCO2, publicCO2, vegmealsCO2, temperatureCO2, solarareaCO2, paperrecyclingCO2, plasticrecyclingCO2},
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




}
