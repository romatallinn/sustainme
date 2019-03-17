package view;

import view.element.FractalTree;
import org.junit.*;

import static org.junit.Assert.*;

public class FractalTreeTest {

    @Test
    public void seedFromNameEmpty() {
        FractalTree testTree = new FractalTree(new int[] {}, "");
        assertEquals(testTree.seedFromName(), 0);
    }

    @Test
    public void seedFromNameA() {
        FractalTree testTree = new FractalTree(new int[] {}, "a");
        assertEquals(testTree.seedFromName(), 97);
    }

    @Test
    public void seedFromNameB() {
        FractalTree testTree = new FractalTree(new int[] {}, "b");
        assertEquals(testTree.seedFromName(), 98);
    }

    @Test
    public void seedFromNameAB() {
        FractalTree testTree = new FractalTree(new int[] {}, "ab");
        assertEquals(testTree.seedFromName(), 97 + 2 * 98);
    }

    @Test
    public void seedFromNameNadyne() {
        FractalTree testTree = new FractalTree(new int[] {}, "nadyne");
        assertEquals(testTree.seedFromName(), 110 + 2 * 97 + 3 * 100 + 4 * 121 + 5 * 110 + 6 * 101);
    }
}
