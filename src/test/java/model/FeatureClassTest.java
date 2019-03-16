package model;
import org.junit.*;
import static org.junit.Assert.*;

public class FeatureClassTest {

    @Test
    public void VegMealTest() {
        SingletonUser.getInstance().init("0");
        FeatureClass.vegMeal(1);
        assertEquals(5, SingletonUser.getInstance().getExperience());
        assertEquals(3.0, SingletonUser.getInstance().getCo2Reduction(),0.0);
        assertEquals(1, SingletonUser.getInstance().getLevel());
    }
    @Test
    public void MultiMealTest() {
        SingletonUser.getInstance().init("0");
        FeatureClass.vegMeal(4);
        assertEquals(10, SingletonUser.getInstance().getExperience());
        assertEquals(12.0, SingletonUser.getInstance().getCo2Reduction(),0.0);
        assertEquals(2, SingletonUser.getInstance().getLevel());
    }


}
