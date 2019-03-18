package controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import view.interfaces.IFoodView;
import view.interfaces.IHomeView;

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
    public void testAddEatedMeals() {

        controller.addEatedVegMeals(1);
        verify(view).displayStatus(any());

    }

}
