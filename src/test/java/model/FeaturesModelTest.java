package model;
import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import org.junit.*;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class FeaturesModelTest {

    @Test
    public void VegMealTest() throws UnsupportedEncodingException, FirebaseException, JacksonUtilityException {
        SingletonUser.getInstance().init();
        new FeaturesModel().vegMeal(1);
        assertEquals(5, SingletonUser.getInstance().getExp());
        assertEquals(3.0, SingletonUser.getInstance().getCo2Reduction(),0.0);
        assertEquals(1, SingletonUser.getInstance().getLevel());
    }
    @Test
    public void MultiMealTest() throws UnsupportedEncodingException, FirebaseException, JacksonUtilityException {
        SingletonUser.getInstance().init();
        new FeaturesModel().vegMeal(4);
        assertEquals(10, SingletonUser.getInstance().getExp());
        assertEquals(12.0, SingletonUser.getInstance().getCo2Reduction(),0.0);
        assertEquals(2, SingletonUser.getInstance().getLevel());
    }


}
