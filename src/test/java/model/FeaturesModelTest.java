package model;
import model.objects.UserProfile;
import org.junit.*;

import static org.junit.Assert.*;

public class FeaturesModelTest {

    FeaturesModel model;

    @Before
    public void setup() {
        model = new FeaturesModel();
    }

//    @Test
//    public void VegMealTest() {
//
//        UserProfile.getInstance().clean();
//
//        model.vegMeal(1);
//        assertEquals((10.0 / 20.0), UserProfile.getInstance().getExpProgress(), 0.1);
//        assertEquals(8.0, UserProfile.getInstance().getCo2Reduction(),0.0);
//        assertEquals(2, UserProfile.getInstance().getLevel());
//        assertEquals(1, model.getVegMealCounter());
//    }
//
//    @Test
//    public void MultiMealTest() {
//
//        UserProfile.getInstance().clean();
//
//        model.vegMeal(2);
//        assertEquals((15.0 / 20.0), UserProfile.getInstance().getExpProgress(), 0.1);
//        assertEquals(11.0, UserProfile.getInstance().getCo2Reduction(),0.0);
//        assertEquals(2, UserProfile.getInstance().getLevel());
//        assertEquals(2, model.getVegMealCounter());
//
//    }




}
