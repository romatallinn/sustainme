package model;
import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import org.junit.*;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class FeaturesModelTest {

    FeaturesModel model;

    @Before
    public void setup() {
        model = new FeaturesModel();
    }

    @Test
    public void VegMealTest() throws UnsupportedEncodingException, FirebaseException {
        SingletonUser.getInstance().init();
        model.vegMeal(1);
        assertEquals((5.0 / 9.0), SingletonUser.getInstance().getExp(), 0.1);
        assertEquals(3.0, SingletonUser.getInstance().getCo2Reduction(),0.0);
        assertEquals(1, SingletonUser.getInstance().getLevel());
    }

    @Test
    public void MultiMealTest() throws UnsupportedEncodingException, FirebaseException {
        SingletonUser.getInstance().init();
        model.vegMeal(4);
        assertEquals((10.0 / 99.0), SingletonUser.getInstance().getExp(), 0.1);
        assertEquals(12.0, SingletonUser.getInstance().getCo2Reduction(),0.0);
        assertEquals(2, SingletonUser.getInstance().getLevel());
    }


}
