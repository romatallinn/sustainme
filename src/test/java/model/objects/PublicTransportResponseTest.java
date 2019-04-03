package model.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PublicTransportResponseTest {

    @Test
    public void getExperienceTest() {
        PublicTransportResponse publicTransportResponse = new PublicTransportResponse(1, 2.5, 7);
        assertEquals(publicTransportResponse.getExperience(), 1);
    }

    @Test
    public void getCo2ReducedTest() {
        PublicTransportResponse publicTransportResponse = new PublicTransportResponse(1, 2.5, 7);
        assertEquals(publicTransportResponse.getCo2Reduced(), 2.5, 0.0001);
    }

    @Test
    public void getAmountTest() {
        PublicTransportResponse publicTransportResponse = new PublicTransportResponse(1, 2.5, 7);
        assertEquals(publicTransportResponse.getDistance(), 7);
    }

    @Test
    public void emptyConstructorTest() {
        PublicTransportResponse publicTransportResponse = new PublicTransportResponse();
        assertEquals(publicTransportResponse.getExperience(), 0);
    }
}
