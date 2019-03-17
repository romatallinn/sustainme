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

    @Test
    public void generatedImageWidth() {
        /**
         * NOTE: Will warn on MacOS Mojave because of a JVM bug:
         * WARNING: NSWindow drag regions should only be invalidated on the Main Thread!
         */
        assertEquals(FractalTree.makeImage(200, 300, new int[] {}, "test").getWidth(), 200);
    }

    @Test
    public void generatedImageHeight() {
        /**
         * NOTE: Will warn on MacOS Mojave because of a JVM bug:
         * WARNING: NSWindow drag regions should only be invalidated on the Main Thread!
         */
        assertEquals(FractalTree.makeImage(200, 300, new int[] {}, "test").getHeight(), 300);
    }

    @Test
    public void generatedImagePixelCenter() {
        /**
         * NOTE: Will warn on MacOS Mojave because of a JVM bug:
         * WARNING: NSWindow drag regions should only be invalidated on the Main Thread!
         */
        assertEquals(FractalTree.makeImage(1, 1, new int[] {}, "test").getRGB(0, 0), 0);
    }
}
