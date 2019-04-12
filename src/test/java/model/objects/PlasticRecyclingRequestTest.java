package model.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlasticRecyclingRequestTest {

    @Test
    public void getUidTest() {
        PlasticRecyclingRequest plasticRecyclingRequest = new PlasticRecyclingRequest("testId", 12.3f);
        assertEquals(plasticRecyclingRequest.getUid(), "testId");
    }

    @Test
    public void getAmountTest() {
        PlasticRecyclingRequest plasticRecyclingRequest = new PlasticRecyclingRequest("testId", 12.3f);
        assertEquals(plasticRecyclingRequest.getAmount(), 12.3, 0.0001);
    }
}
