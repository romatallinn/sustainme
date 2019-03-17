package model.element;

import static com.jogamp.opengl.math.FloatUtil.PI;
import static com.jogamp.opengl.math.FloatUtil.cos;
import static com.jogamp.opengl.math.FloatUtil.sin;
import static java.util.Arrays.copyOfRange;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.JFrame;


public class FractalTree implements GLEventListener {

    int[] scores;
    String name;
    static float startLength = 0.4f;
    static float degreeOffset = 30f;
    static float lengthFactor = 0.73f;


    /**
     * Constructor
     *
     * @param scores - Sets depth of fractal tree
     * @param name   - Sets name to generate random seed
     */
    public FractalTree(int[] scores, String name) {

        this.scores = scores;
        this.name = name;
    }

    /**
     * Starts drawing process
     *
     * @param drawable - opengl variable
     */
    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glBegin(GL2.GL_LINES);

        // drawBranch(gl, Direction.N, 0f, -1f, 0.5f, 60000);
        drawTree(gl, this.scores);
        gl.glFlush();
    }

    /**
     * Gives variables for the drawDegBranch method.
     *
     * @param gl    - opengl variable
     * @param depth - depth of tree (scores)
     */
    public void drawTree(
        GL2 gl,
        int[] depth
    ) {
        drawDegBranch(gl, 0f, 0f, -1f, depth);
    }

    /**
     * Calculates a number used for the random seed calculation.
     *
     * @return - the calculated number that the seed uses
     */
    public long seedFromName() {
        char[] chars = this.name.toCharArray();
        long seed = 0;

        for (int i = 0; i < chars.length; i++) {
            seed += (i + 1) * chars[i];
        }

        return seed;
    }

    /**
     * Makes the calculations for where to draw the lines (With starting position).
     *
     * @param gl        - opengl variable
     * @param direction - direction of the line
     * @param startX    - start x-coordinate
     * @param startY    - start y-coordinate
     * @param depth     - depth of tree (scores)
     */
    public void drawDegBranch(GL2 gl,
                              float direction,
                              float startX,
                              float startY,
                              int[] depth
    ) {
        drawDegBranch(
            gl,
            direction,
            startX,
            startY,
            FractalTree.startLength,
            depth,
            seedFromName()
        );
    }

    /**
     * Makes the calculations for where to draw the lines (recursively).
     *
     * @param gl         - opengl variable
     * @param direction  - direction of the line
     * @param startX     - start x-coordinate
     * @param startY     - start y-coordinate
     * @param lineLength - length of the lines
     * @param depth      - depth of tree (scores)
     * @param randomSeed - randomseed for the current depth
     */
    public void drawDegBranch(GL2 gl,
                              float direction,
                              float startX,
                              float startY,
                              float lineLength,
                              int[] depth,
                              long randomSeed
    ) {
        if (depth.length == 0 || (depth.length == 1 && depth[0] <= 0)) {
            return;
        }


        // Changes direction when a new line gets drawn
        direction = direction < 0f ? direction + 360f : direction;
        float rad = direction / 180f * PI;
        float nextX = startX + sin(rad) * lineLength;
        float nextY = startY + cos(rad) * lineLength;

        gl.glBegin(GL2.GL_LINES);
        gl.glColor3f(0.3f, 1f, 0.3f);
        gl.glVertex3f(startX, startY, 0);
        gl.glVertex3f(nextX, nextY, 0);
        gl.glEnd();

        float nextLen = lineLength * FractalTree.lengthFactor;

        int[] depthLeft;
        int[] depthRight;
        if (depth.length > 1) {
            depthLeft = copyOfRange(depth, 0, depth.length / 2);
            depthRight = copyOfRange(depth, depth.length / 2, depth.length);
        } else {
            depthLeft = new int[] {(depth[0] - 1) / 2};
            depthRight = new int[] {(depth[0] - 1) / 2};
        }

        // Make random from seed
        Random seededRandom = new Random(randomSeed);

        // Draw left branch
        drawDegBranch(
            gl,
            direction - FractalTree.degreeOffset * seededRandom.nextFloat(),
            nextX,
            nextY,
            nextLen,
            depthLeft,
            randomSeed + 'L' // Update seed by adding L (108) for left
        );
        // Draw right branch
        drawDegBranch(
            gl,
            direction + FractalTree.degreeOffset * seededRandom.nextFloat(),
            nextX,
            nextY,
            nextLen,
            depthRight,
            randomSeed + 'R' // Update seed by adding R (114) for right
        );

    }

    /**
     * Unused opengl event handler.
     *
     * @param gl - opengl variable
     */
    // @Override
    public void dispose(GLAutoDrawable gl) {
        // should never be called
    }

    /**
     * Unused opengl event handler.
     *
     * @param gl - opengl variable
     */
    // @Override
    public void init(GLAutoDrawable gl) {
        // should never be called
    }

    /**
     * Unused opengl event handler.
     *
     * @param gl     - opengl variable
     * @param x      - x-coordinate for reshape
     * @param y      - y-coordinate for reshape
     * @param width  - width for reshape
     * @param height - heigt for reshape
     */
    // @Override
    public void reshape(GLAutoDrawable gl, int x, int y, int width, int height) {
        // should never be called
    }

    /**
     * Makes a buffered image
     *
     * @param width  - sets image width
     * @param height - sets image height
     * @param scores - sets depth of fractal tree
     * @param name   - used for seed calculation
     * @return
     */
    public static BufferedImage makeImage(int width, int height, int[] scores, String name) {

        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        // Make Gl canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        FractalTree fractalTreeDrawer = new FractalTree(scores, name);
        glcanvas.addGLEventListener(fractalTreeDrawer);
        glcanvas.setSize(width, height);

        // Make image of canvas
        Image canvasImage = glcanvas.createImage(width, height);

        // Create a buffered image
        BufferedImage bufferedImage = new BufferedImage(canvasImage.getWidth(null), canvasImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the buffered image
        Graphics2D drawer = bufferedImage.createGraphics();
        drawer.drawImage(canvasImage, 0, 0, null);
        drawer.dispose();

        // Return the buffered image
        return bufferedImage;

    }

}



