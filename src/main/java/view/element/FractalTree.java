package view.element;

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

import java.util.Random;
import javax.swing.JFrame;


public class FractalTree implements GLEventListener {

    // @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glBegin(GL2.GL_LINES);

        // drawBranch(gl, Direction.N, 0f, -1f, 0.5f, 60000);
        drawTree(gl, 0.4f, 30f, 0.73f, new int[] {10, 10, 2, 60, 100, 21, 10});
        gl.glFlush();
    }

    public void drawTree(
        GL2 gl,
        float startLength,
        float degreeOffset,
        float lengthFactor,
        int[] depth
    ) {
        drawDegBranch(gl, 0f, 0f, -1f, startLength, degreeOffset, lengthFactor, depth);
    }

    public static long seedFromString(String sl) {
        char[] cl = sl.toCharArray();
        long rl = 0;

        for (int i = 0; i < cl.length; i++) {
            rl += (i + 1) * cl[i];
        }

        return rl;
    }

    public void drawDegBranch(GL2 gl,
                              float dl,
                              float xl,
                              float yl,
                              float ll,
                              float dd,
                              float fl,
                              int[] depth
    ) {
        drawDegBranch(gl, dl, xl, yl, ll, dd, fl, depth, new Random(seedFromString("Roman")));
    }

    public void drawDegBranch(GL2 gl,
                              float dl,
                              float xl,
                              float yl,
                              float ll,
                              float dd,
                              float fl,
                              int[] depth,
                              Random rl
    ) {
        if (depth.length == 0 || (depth.length == 1 && depth[0] <= 0)) {
            return;
        }

        dl = dl < 0f ? dl + 360f : dl;
        float rad = dl / 180f * PI;
        float nextX = xl + sin(rad) * ll;
        float nextY = yl + cos(rad) * ll;

        gl.glBegin(GL2.GL_LINES);
        gl.glColor3f(0.3f, 1f, 0.3f);
        gl.glVertex3f(xl, yl, 0);
        gl.glVertex3f(nextX, nextY, 0);
        gl.glEnd();

        float nextLen = ll * fl;

        int[] depthLeft;
        int[] depthRight;
        if (depth.length > 1) {
            depthLeft = copyOfRange(depth, 0, depth.length / 2);
            depthRight = copyOfRange(depth, depth.length / 2, depth.length);
        } else {
            depthLeft = new int[] {(depth[0] - 1) / 2};
            depthRight = new int[] {(depth[0] - 1) / 2};
        }

        drawDegBranch(gl, dl - dd * rl.nextFloat(), nextX, nextY, nextLen, dd, fl, depthLeft, rl);
        drawDegBranch(gl, dl + dd * rl.nextFloat(), nextX, nextY, nextLen, dd, fl, depthRight, rl);

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
        FractalTree ll = new FractalTree();
        glcanvas.addGLEventListener(ll);
        glcanvas.setSize(400, 400);

        //creating frame
        final JFrame frame = new JFrame("local.nadyne.joglproj.Triangle");

        //adding canvas to frame
        frame.getContentPane().add(glcanvas);

        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }

}

