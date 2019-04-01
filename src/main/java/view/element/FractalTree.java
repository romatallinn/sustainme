package view.element;


import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.util.Arrays.copyOfRange;

import javafx.geometry.Point3D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

import java.util.Random;


public class FractalTree {
    private static final double degreeOffset = 30;
    private static final double lengthFactor = 0.73; // Percentage of previous length in recursion
    private static final double startLength = 0.22; // Percentage of canvas height
    private static final double startWidth = 0.03125; // Percentage of canvas width
    private static final double blendSpeed = 0.15;
    private static final Color startColor = Color.BROWN;

    private int[] scores;
    private String seed;
    private Color[] colors;

    public FractalTree(String seed) {
        this(seed, new int[] {}, new Color[] {});
    }

    public FractalTree(String seed, int[] scores, Color[] colors) {
        setScores(scores, colors);
        this.seed = seed;
    }

    public int[] getScores() {
        return scores;
    }

    /**
     * Sets the scores and makes sure that there are as much colors as scores.
     * @param scores - sets depth of the tree with the scores
     * @param colors - gives every category a specified color
     */
    public void setScores(int[] scores, Color[] colors) {
        if (scores.length > colors.length) {
            throw new IllegalArgumentException("FractalTree colors cannot be smaller than scores");
        }
        this.scores = scores;
        this.colors = colors;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public void drawTree(Canvas canvas) {
        drawTree(canvas, 0, 0, canvas.getWidth(), canvas.getHeight());
    }

    /**
     * Starts drawing the tree in the canvas.
     * @param canvas    - javafx canvas
     * @param startX    - start x-coordinate
     * @param startY    - start y-coordinate
     * @param width     - width of canvas
     * @param height    - height of canvas
     */
    public void drawTree(Canvas canvas, double startX, double startY, double width, double height) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Point3D vec = new Point3D(width / 2, height, deg(180));
        drawBranch(
            gc,
            vec,
            startLength * height,
            scores,
            startColor,
            colors,
            seedAsLong(),
            0
        );
    }

    private void drawBranch(
        GraphicsContext gc,
        Point3D vec,
        double length,
        int[] scores,
        Color color,
        Color[] colors,
        long randomSeed,
        int level
    ) {
        if (scores.length == 0 || (scores.length == 1 && scores[0] <= 0)) {
            return;
        }

        // Changes direction when a new line gets drawn
        double rad = vec.getZ() / 180 * PI;
        double nextX = vec.getX() + sin(rad) * length;
        double nextY = vec.getY() + cos(rad) * length;

        Color local = fade(color, colors.length > 1 ? startColor : colors[0]);
        gc.setStroke(local);
        double width = startWidth * gc.getCanvas().getWidth() - (level * 3.25);
        gc.setLineWidth(width < 1 ? 1 : width);
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.strokeLine(vec.getX(), vec.getY(), nextX, nextY);

        double nextLength = length * lengthFactor;

        // Split scores into two sets
        int[] scoresLeft;
        Color[] colorsLeft;
        int[] scoresRight;
        Color[] colorsRight;

        if (scores.length > 1) {
            scoresLeft = copyOfRange(scores, 0, scores.length / 2);
            colorsLeft = copyOfRange(colors, 0, scores.length / 2);
            scoresRight = copyOfRange(scores, scores.length / 2, scores.length);
            colorsRight = copyOfRange(colors, scores.length / 2, scores.length);
        } else {
            scoresLeft = new int[] {(scores[0] - 1) / 2};
            colorsLeft = new Color[] {colors[0]};
            scoresRight = new int[] {(scores[0] - 1) / 2};
            colorsRight = new Color[] {colors[0]};
        }

        // Make random from seed
        Random random = new Random(randomSeed);

        // Stop drawing when lines get too small to see
        if (nextLength < 0.4) {
            return;
        }

        // Draw left branch
        drawBranch(
            gc,
            new Point3D(
                nextX,
                nextY,
                deg(vec.getZ() - degreeOffset * random.nextDouble())
            ),
            nextLength,
            scoresLeft,
            local,
            colorsLeft,
            randomSeed + 857 * level, // Update seed by adding prime 857 for left
            level + 1
        );

        // Draw right branch
        drawBranch(
            gc,
            new Point3D(
                nextX,
                nextY,
                deg(vec.getZ() + degreeOffset * random.nextDouble())
            ),
            nextLength,
            scoresRight,
            local,
            colorsRight,
            randomSeed + 151 * level, // Update seed by adding prime 151 for right
            level + 1
        );
    }

    /**
     * Calculates a number used for the random seed calculation.
     *
     * @return - the calculated number that the seed uses
     */
    public long seedAsLong() {
        char[] chars = this.seed.toCharArray();
        long seed = 0;

        for (int i = 0; i < chars.length; i++) {
            seed += (i + 1) * chars[i];
        }

        return seed;
    }

    /**
     * Makes sure that the angle between the branches has a normal value.
     * @param in    - angle between the branches
     * @return      - new value for the degrees
     */
    public double deg(double in) {
        if (in < 0) {
            return in + 360;
        }
        if (in >= 360) {
            return in - 360;
        }
        return in;
    }

    /**
     * Fades the color of the branches along the growth of the tree.
     * @param source - original color
     * @param target - color that it must become
     * @return       - new color
     */
    public Color fade(Color source, Color target) {
        return new Color(
            source.getRed() * (1.0 - blendSpeed) + target.getRed() * blendSpeed,
            source.getGreen() * (1.0 - blendSpeed) + target.getGreen() * blendSpeed,
            source.getBlue() * (1.0 - blendSpeed) + target.getBlue() * blendSpeed,
            source.getOpacity() * (1.0 - blendSpeed) + target.getOpacity() * blendSpeed
        );
    }
}
