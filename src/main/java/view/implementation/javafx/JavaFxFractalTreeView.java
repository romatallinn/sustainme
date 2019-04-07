package view.implementation.javafx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.UserProfile;

import model.objects.FractalTreeRequest;
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


        // Scores: bike-fuchsia, localProduce-blueviolet, publicTransport-azure,
        // veggie-lime, temperature-darkorange, solarPanels-red
        FractalTree ft = new FractalTree(
                firstName,
                new double[]{30, 39, 20, 40, 450, 500},
                new Color[]{
                    Color.FUCHSIA,
                    Color.BLUEVIOLET,
                    Color.AZURE,
                    Color.LIME,
                    Color.DARKORANGE,
                    Color.RED}
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
