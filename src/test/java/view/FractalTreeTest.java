package view;

import javafx.scene.paint.Color;
import view.element.FractalTree;
import org.junit.*;

import static org.junit.Assert.*;

public class FractalTreeTest {

    @Test
    public void seedAsLongEmpty() {
        FractalTree testTree = new FractalTree("");
        assertEquals(testTree.seedAsLong(), 0);
    }

    @Test
    public void seedAsLongA() {
        FractalTree testTree = new FractalTree("a");
        assertEquals(testTree.seedAsLong(), 97);
    }

    @Test
    public void seedAsLongB() {
        FractalTree testTree = new FractalTree("b");
        assertEquals(testTree.seedAsLong(), 98);
    }

    @Test
    public void seedAsLongAB() {
        FractalTree testTree = new FractalTree("ab");
        assertEquals(testTree.seedAsLong(), 97 + 2 * 98);
    }

    @Test
    public void seedAsLongNadyne() {
        FractalTree testTree = new FractalTree("nadyne");
        assertEquals(testTree.seedAsLong(), 110 + 2 * 97 + 3 * 100 + 4 * 121 + 5 * 110 + 6 * 101);
    }

    @Test
    public void deg() {
        FractalTree testTree = new FractalTree("nadyne");
        assertEquals(testTree.deg(93), 93, 0);
    }

    @Test
    public void degMin() {
        FractalTree testTree = new FractalTree("nadyne");
        assertEquals(testTree.deg(-23), -23 + 360, 0);
    }

    @Test
    public void degMax() {
        FractalTree testTree = new FractalTree("nadyne");
        assertEquals(testTree.deg(379), 379 - 360, 0);
    }

    @Test
    public void degNull() {
        FractalTree testTree = new FractalTree("nadyne");
        assertEquals(testTree.deg(0), 0, 0);
    }

    @Test
    public void degFull() {
        FractalTree testTree = new FractalTree("nadyne");
        assertEquals(testTree.deg(360), 360 - 360, 0);
    }

    @Test
    public void fadeBlue() {
        FractalTree testTree = new FractalTree("nadyne");
        Color faded = testTree.fade(
            new Color(0.3, 0.8, 0.3, 0.9), new Color(0.8, 0.2, 0.5, 0.3));
        assertEquals(faded.getBlue(), 0.33000001311302185, 0);

    }

    @Test
    public void fadeRed() {
        FractalTree testTree = new FractalTree("nadyne");
        Color faded = testTree.fade(
            new Color(0.3, 0.8, 0.3, 0.9), new Color(0.8, 0.2, 0.5, 0.3) );
        assertEquals(faded.getRed(), 0.375, 0);

    }

    @Test
    public void fadeGreen() {
        FractalTree testTree = new FractalTree("nadyne");
        Color faded = testTree.fade(
            new Color(0.3, 0.8, 0.3, 0.9), new Color(0.8, 0.2, 0.5, 0.3) );
        assertEquals(faded.getGreen(), 0.7100000381469727, 0);

    }

    @Test
    public void fadeOpacity() {
        FractalTree testTree = new FractalTree("nadyne");
        Color faded = testTree.fade(
            new Color(0.3, 0.8, 0.3, 0.9), new Color(0.8, 0.2, 0.5, 0.3) );
        assertEquals(faded.getOpacity(), 0.8100000023841858, 0);

    }

}

