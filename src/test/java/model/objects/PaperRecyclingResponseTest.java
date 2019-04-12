package model.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PaperRecyclingResponseTest {


    @Test
    public void getExperienceTest() {
        PaperRecyclingResponse paperRecyclingResponse = new PaperRecyclingResponse(1, 2.5, 7.4);
        assertEquals(paperRecyclingResponse.getExperience(), 1);
    }

    @Test
    public void getCo2ReducedTest() {
        PaperRecyclingResponse paperRecyclingResponse = new PaperRecyclingResponse(1, 2.5, 7.4);
        assertEquals(paperRecyclingResponse.getCo2Reduced(), 2.5, 0.0001);
    }

    @Test
    public void getAmountTest() {
        PaperRecyclingResponse paperRecyclingResponse = new PaperRecyclingResponse(1, 2.5, 7.4);
        assertEquals(paperRecyclingResponse.getAmount(), 7.4, 0.0001);
    }

    @Test
    public void emptyConstructorTest() {
        PaperRecyclingResponse paperRecyclingResponse = new PaperRecyclingResponse();
        assertEquals(paperRecyclingResponse.getExperience(), 0);
    }
}


