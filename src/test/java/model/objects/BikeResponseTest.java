package model.objects;

import org.junit.*;
import static org.junit.Assert.*;

public class BikeResponseTest {

    @Test
    public void getExperienceTest() {
        BikeResponse bikeResponse = new BikeResponse(1, 2.5, 7);
        assertEquals(bikeResponse.getExperience(), 1);
    }

    @Test
    public void getCo2ReducedTest() {
        BikeResponse bikeResponse = new BikeResponse(1, 2.5, 7);
        assertEquals(bikeResponse.getCo2Reduced(), 2.5, 0.0001);
    }

    @Test
    public void getAmountTest() {
        BikeResponse bikeResponse = new BikeResponse(1, 2.5, 7);
        assertEquals(bikeResponse.getDistance(), 7);
    }

    @Test
    public void emptyConstructorTest() {
        BikeResponse bikeResponse = new BikeResponse();
        assertEquals(bikeResponse.getExperience(), 0);
    }

}
