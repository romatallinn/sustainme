package view.implementation.javafx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.FractalTreeModel;
import model.UserProfile;



import model.objects.FractalTreeResponse;
import view.element.FractalTree;



public class JavaFxFractalTreeView extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Creates the canvas
        Canvas canvas = new Canvas(800, 800);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BEIGE);
        gc.fillRect(0, 0, 200, 800);
        gc.setFill(Color.BLACK);
        gc.fillRect(200, 0, 200, 800);
        gc.setFill(Color.BEIGE);
        gc.fillRect(400, 0, 200, 800);
        gc.setFill(Color.BLACK);
        gc.fillRect(600, 0, 200, 800);

        String firstName = UserProfile.getInstance().getFirstName();
        FractalTreeResponse result = new FractalTreeModel().fractalTreeGetData();
        double bikeCO2 = result.getBikeCo2();
        double vegmealsCO2 = result.getVegmealsCO2();
        double localproduceCO2 = result.getLocalproduceCO2();
        double publicCO2 = result.getPublicCO2();
        // TO DO: temp, sol, paper, plastic


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
        Group root = new Group();
        root.getChildren().add(canvas);


        // Instantiate the view
        primaryStage.setTitle("SustainMe - Fractal FractalTree");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }




}
