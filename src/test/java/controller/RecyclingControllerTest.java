package controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import view.interfaces.IRecyclingView;

public class RecyclingControllerTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private IRecyclingView view;

    private RecyclingController controller;

    @Before
    public void setup() {
        controller = new RecyclingController(view);
    }

    @Test
    public void testUpdateViewWithData() {
        controller.updateViewWithDate();
        Mockito.verify(view).updatePaperRecyclingCounter(Mockito.anyDouble());
    }

    @Test
    public void testAddPaperRecyclingFailureParse() {
        controller.addPaperRecycling("fff");
        Mockito.verify(view).displayStatusPaper("Please enter a number.");
    }

    @Test
    public void testAddPaperRecyclingFailureRangeBelow() {
        controller.addPaperRecycling("-1");
        Mockito.verify(view).displayStatusPaper("Please enter the weight above 0 kg");
    }

    @Test
    public void testAddPaperRecyclingSuccess() {
        controller.addPaperRecycling("1");
        Mockito.verify(view).displayStatusPaper("The stat of recycled paper is updated!");
    }

    @Test
    public void testAddPlasticRecyclingFailureParse() {
        controller.addPlasticRecycling("fff");
        Mockito.verify(view).displayStatusPlastic("Please enter a number.");
    }

    @Test
    public void testAddPlasticRecyclingFailureRangeBelow() {
        controller.addPlasticRecycling("-1");
        Mockito.verify(view).displayStatusPlastic("Please enter the weight above 0 kg");
    }

    @Test
    public void testAddPlasticRecyclingSuccess() {
        controller.addPlasticRecycling("1");
        Mockito.verify(view).displayStatusPlastic("The stat of recycled plastic is updated!");
    }

}
