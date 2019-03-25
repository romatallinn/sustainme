package model.objects;

import org.junit.*;
import static org.junit.Assert.*;

public class VegetarianResponseTest {

    @Test
    public void getExperienceTest() {
        VegetarianResponse vegetarianResponse = new VegetarianResponse(1, 2.5, 7);
        assertEquals(vegetarianResponse.getExperience(), 1);
    }

    @Test
    public void getCo2ReducedTest() {
        VegetarianResponse vegetarianResponse = new VegetarianResponse(1, 2.5, 7);
        assertEquals(vegetarianResponse.getCo2Reduced(), 2.5, 0.0001);
    }

    @Test
    public void getAmountTest() {
        VegetarianResponse vegetarianResponse = new VegetarianResponse(1, 2.5, 7);
        assertEquals(vegetarianResponse.getAmount(), 7);
    }

    @Test
    public void emptyConstructorTest() {
        VegetarianResponse vegetarianResponse = new VegetarianResponse();
        assertEquals(vegetarianResponse.getExperience(), 0);
    }

}
