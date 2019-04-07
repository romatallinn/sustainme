package model.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PaperRecyclingRequestTest {

    @Test
    public void getUidTest() {
        PaperRecyclingRequest paperRecyclingRequest = new PaperRecyclingRequest("testId", 12.3);
        assertEquals(paperRecyclingRequest.getUid(), "testId");
    }

    @Test
    public void getWeightTest() {
        PaperRecyclingRequest paperRecyclingRequest = new PaperRecyclingRequest("testId", 12.3);
        assertEquals(paperRecyclingRequest.getAmount(), 12.3, 0.0001);
    }
}
