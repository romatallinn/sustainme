package model.objects;

import org.junit.*;
import static org.junit.Assert.*;

public class BikeRequestTest {

    @Test
    public void getUidTest() {
        BikeRequest bikeRequest = new BikeRequest("testId", 1);
        assertEquals(bikeRequest.getUid(), "testId");
    }

    @Test
    public void getDistanceTest() {
        BikeRequest bikeRequest = new BikeRequest("testId", 1);
        assertEquals(bikeRequest.getDistance(), 1);
    }

}
