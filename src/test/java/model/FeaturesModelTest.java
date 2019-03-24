package model;

import org.junit.*;


public class FeaturesModelTest {

    FeaturesModel model;

    @Before
    public void setup() {
        model = new FeaturesModel();
    }


    @Test
    public void testGetter() {
        FeaturesModel emptyModel = new FeaturesModel();
        Assert.assertEquals(0, emptyModel.getVegMealCounter());
    }

}
