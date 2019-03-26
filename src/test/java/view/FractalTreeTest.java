package view;

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
}
