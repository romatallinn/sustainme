package view.element;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import javax.swing.JFrame;
import java.util.Random;
import static com.jogamp.opengl.math.FloatUtil.*;
import static java.util.Arrays.copyOfRange;

public class FractalTree implements GLEventListener {

    // @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glBegin (GL2.GL_LINES);

        // drawBranch(gl, Direction.N, 0f, -1f, 0.5f, 60000);
        drawTree(gl, 0.4f, 30f, 0.73f, new int[]{10, 10, 2, 60, 100, 21, 10});
        gl.glFlush();
    }

    public void drawTree(GL2 g, float startLength, float degreeOffset, float lengthFactor, int[] depth) {
        drawDegBranch(g, 0f, 0f, -1f, startLength, degreeOffset, lengthFactor, depth);
    }

    public static long seedFromString(String s) {
        char[] c = s.toCharArray();
        long r = 0;

        for (int i = 0; i < c.length; i++) {
            r += (i + 1) * c[i];
        }

        return r;
    }

    public void drawDegBranch(GL2 g, float d, float x, float y, float l, float dd, float fl, int[] depth) {
        drawDegBranch(g, d, x, y, l, dd, fl, depth, new Random(seedFromString("Roman")));
    }
    public void drawDegBranch(GL2 g, float d, float x, float y, float l, float dd, float fl, int[] depth, Random r) {
        if (depth.length == 0 || (depth.length == 1 && depth[0] <= 0)) {
            return;
        }

        d = d < 0f ? d + 360f : d;
        float rad = d / 180f * PI;
        float nextX = x + sin(rad) * l;
        float nextY = y + cos(rad) * l;

        g.glBegin (GL2.GL_LINES);
        g.glColor3f(0.3f, 1f, 0.3f);
        g.glVertex3f(x, y, 0);
        g.glVertex3f(nextX, nextY,0);
        g.glEnd();

        float nextLen = l * fl;

        int[] depthLeft;
        int[] depthRight;
        if (depth.length > 1) {
            depthLeft = copyOfRange(depth, 0, depth.length / 2);
            depthRight = copyOfRange(depth, depth.length / 2, depth.length);
        } else {
            depthLeft = new int[]{(depth[0] - 1) / 2};
            depthRight = new int[]{(depth[0] - 1) / 2};
        }

        drawDegBranch(g, d - dd * r.nextFloat(), nextX, nextY, nextLen, dd, fl, depthLeft, r);
        drawDegBranch(g, d + dd * r.nextFloat(), nextX, nextY, nextLen, dd, fl, depthRight, r);

    }

    // @Override
    public void dispose(GLAutoDrawable arg0) {
        //method body
    }

    // @Override
    public void init(GLAutoDrawable arg0) {
        // method body
    }

    // @Override
    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
        // method body
    }

    public static void main(String[] args) {

        System.out.println(sin(30));

        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        FractalTree l = new FractalTree();
        glcanvas.addGLEventListener(l);
        glcanvas.setSize(400, 400);

        //creating frame
        final JFrame frame = new JFrame ("local.nadyne.joglproj.Triangle");

        //adding canvas to frame
        frame.getContentPane().add(glcanvas);

        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);

    }//end of main

}//end of classimport javax.media.opengl.GL2;
