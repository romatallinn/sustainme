package model.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LocalProduceResponseTest {

    @Test
    public void getExperienceTest() {
        LocalProduceResponse localProduceResponse = new LocalProduceResponse(1, 2.5, 7.4f);
        assertEquals(localProduceResponse.getExperience(), 1);
    }

    @Test
    public void getCo2ReducedTest() {
        LocalProduceResponse localProduceResponse = new LocalProduceResponse(1, 2.5, 7.4f);
        assertEquals(localProduceResponse.getCo2Reduced(), 2.5, 0.0001);
    }

    @Test
    public void getAmountTest() {
        LocalProduceResponse localProduceResponse = new LocalProduceResponse(1, 2.5, 7.4f);
        assertEquals(localProduceResponse.getAmount(), 7.4, 0.0001);
    }

    @Test
    public void emptyConstructorTest() {
        LocalProduceResponse localProduceResponse = new LocalProduceResponse();
        assertEquals(localProduceResponse.getExperience(), 0);
    }
}
