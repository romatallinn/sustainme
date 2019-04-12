package model.objects;

import org.junit.*;
import static org.junit.Assert.*;

public class FractalTreeRequestTest {
    @Test
    public void getUidTest() {
        FractalTreeRequest fractalTreeRequest = new FractalTreeRequest("testId");
        assertEquals(fractalTreeRequest.getUid(), "testId");
    }
}
