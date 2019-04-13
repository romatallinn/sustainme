package controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import supporting.FirebaseAuth;
import view.interfaces.IFoodView;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class FoodControllerTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private IFoodView view;

    private FoodController controller;

    @Before
    public void setup() {
        controller = new FoodController(view);
    }

    @Test
    public void testUpdate() {
        controller.updateViewWithData();
        Mockito.verify(view).updateLocalProduceCounter(Mockito.anyDouble());
        Mockito.verify(view).updateVegCounter(Mockito.anyInt());
    }

    @Test
    public void testVegMealsAddFailureParse() {
        controller.addEatenVegMeals("fff");
        Mockito.verify(view).displayStatusVegetarian("Please enter an integer number.");
    }

    @Test
    public void testVegMealsAddFailureRange() {
        controller.addEatenVegMeals("10000");
        Mockito.verify(view).displayStatusVegetarian("Please enter a  number in the range 0-3 meals.");
    }

    @Test
    public void testVegMealsAddFailureRangeBelow() {
        controller.addEatenVegMeals("-1");
        Mockito.verify(view).displayStatusVegetarian("Please enter a  number in the range 0-3 meals.");
    }

    @Test
    public void testVegMealsAddSuccess() {
        controller.addEatenVegMeals("1");
        Mockito.verify(view).displayStatusVegetarian("The stat of the vegetarian meals is updated!");
    }

    @Test
    public void testLocalProduceAddFailureParse() {
        controller.addEatenLocalProduce("fff");
        Mockito.verify(view).displayStatusLocal("Please enter a number.");
    }

    @Test
    public void testLocalProduceAddFailureRange() {
        controller.addEatenLocalProduce("10000");
        Mockito.verify(view).displayStatusLocal("Please enter the weight in the range 0-10kg.");
    }

    @Test
    public void testLocalProduceAddFailureRangeBelow() {
        controller.addEatenLocalProduce("-1");
        Mockito.verify(view).displayStatusLocal("Please enter the weight in the range 0-10kg.");
    }

    @Test
    public void testLocalProduceAddSuccess() {
        controller.addEatenLocalProduce("1");
        Mockito.verify(view).displayStatusLocal("The stat of the local produce is updated!");
    }

}
