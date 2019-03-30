package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TransportModelTest {

    private TransportModel model;

    @Before
    public void setup() {
        model = new TransportModel();
    }

    @Test
    public void testGetters() {
        Assert.assertEquals(0, model.getBikeDistance());
    }

}
