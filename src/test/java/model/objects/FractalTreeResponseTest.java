package model.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FractalTreeResponseTest {

    @Test
    public void getPublicCo2() {
        FractalTreeResponse fractalTreeResponse = new FractalTreeResponse(1, 2.5, 7, 3.4, 8, 9, 4, 6);
        assertEquals(fractalTreeResponse.getPublicCO2(), 3.4, 1);
    }

    @Test
    public void getVegmealsCo2() {
        FractalTreeResponse fractalTreeResponse = new FractalTreeResponse(1, 2.5, 7, 3.4, 8, 9, 4, 6);
        assertEquals(fractalTreeResponse.getVegmealsCO2(), 2.5, 1);
    }

    @Test
    public void getLocalproduceCo2() {
        FractalTreeResponse fractalTreeResponse = new FractalTreeResponse(1, 2.5, 7, 3.4, 8, 9, 4, 6);
        assertEquals(fractalTreeResponse.getLocalproduceCO2(), 7, 1);
    }

    @Test
    public void getBikeCo2() {
        FractalTreeResponse fractalTreeResponse = new FractalTreeResponse(1, 2.5, 7, 3.4, 8, 9, 4, 6);
        assertEquals(fractalTreeResponse.getBikeCo2(), 1, 1);
    }

    @Test
    public void getPlasticrecyclingCO2() {
        FractalTreeResponse fractalTreeResponse = new FractalTreeResponse(1, 2.5, 7, 3.4, 8, 9, 4, 6);
        assertEquals(fractalTreeResponse.getPlasticrecyclingCO2(), 8, 1);
    }

    @Test
    public void getPaperrecyclingCO2() {
        FractalTreeResponse fractalTreeResponse = new FractalTreeResponse(1, 2.5, 7, 3.4, 8, 9, 4, 6);
        assertEquals(fractalTreeResponse.getPaperrecyclingCO2(), 9, 1);
    }

}
