package view.implementation.javafx;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import view.element.FractalTree;

import java.awt.image.BufferedImage;


public class JavaFxFractalTreeView extends Application {

    @Override
    public void start(Stage primaryStage) {

        BufferedImage fractalTree = FractalTree.makeImage(300, 200, new int[] {3, 90}, "nadyne");
        WritableImage fractalTreeImage = SwingFXUtils.toFXImage(fractalTree, null);

        // Creates the canvas
        Group root = new Group();
        Canvas canvas = new Canvas(300, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(fractalTreeImage, 0, 0);
        root.getChildren().add(canvas);


        // Instantiate the view
        primaryStage.setTitle("SustainMe - Fractal Tree");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }




}
