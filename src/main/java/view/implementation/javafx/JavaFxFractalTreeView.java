package view.implementation.javafx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.element.FractalTree;

import java.awt.image.BufferedImage;


public class JavaFxFractalTreeView extends Application {

    @Override
    public void start(Stage primaryStage) {

//        BufferedImage fractalTree = FractalTreeOld.makeImage(300, 200, new int[] {3, 90}, "nadyne");
//        WritableImage fractalTreeImage = SwingFXUtils.toFXImage(fractalTree, null);

        // Creates the canvas
        Group root = new Group();
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

        FractalTree ft = new FractalTree(
                "paul",
                new int[]{250, 250, 2000, 2000, 10000},
                new Color[]{new Color(0.953, 0.694, 0.31, 1), Color.PALEGREEN, Color.BLUE, Color.RED, Color.PINK}
        );
        ft.drawTree(canvas);
//        gc.drawImage(fractalTreeImage, 0, 0);
        root.getChildren().add(canvas);


        // Instantiate the view
        primaryStage.setTitle("SustainMe - Fractal FractalTree");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }




}
