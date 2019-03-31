package model.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PublicTransportRequestTest {

    @Test
    public void getUidTest() {
        PublicTransportRequest publicTransportRequest = new PublicTransportRequest("testId", 1, true);
        assertEquals(publicTransportRequest.getUid(), "testId");
    }

    @Test
    public void getDistanceTest() {
        PublicTransportRequest publicTransportRequest = new PublicTransportRequest("testId", 1, true);
        assertEquals(publicTransportRequest.getDistance(), 1);
    }

    @Test
    public void getTypeTest() {
        PublicTransportRequest publicTransportRequest = new PublicTransportRequest("testId", 1, true);
        assertEquals(publicTransportRequest.getType(), true);
    }
}
