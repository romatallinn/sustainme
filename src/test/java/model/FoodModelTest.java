package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FoodModelTest {

    private UserProfile profile;
    private FoodModel model;

    @Before
    public void setup() {
        profile = new UserProfile();
        model = new FoodModel();
    }

    @Test
    public void testGetters() {
        Assert.assertEquals(0, model.getVegMealsCount());
        Assert.assertEquals(0, model.getLocalProduceCount(), 0);
    }


}
