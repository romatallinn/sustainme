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
        Mockito.verify(view).updatePaperRecyclingCounter(Mockito.anyFloat());
    }

    @Test
    public void testAddPaperRecyclingFailureParse() {
        controller.addPaperRecycling("fff");
        Mockito.verify(view).displayStatus(Mockito.anyString());
    }

    @Test
    public void testAddPaperRecyclingFailureRange() {
        controller.addPaperRecycling("10000");
        Mockito.verify(view).displayStatus(Mockito.anyString());
    }

    @Test
    public void testAddPaperRecyclingSuccess() {
        controller.addPaperRecycling("1");
        Mockito.verify(view).displayStatus(Mockito.anyString());
    }

    @Test
    public void testAddPlasticRecyclingFailureParse() {
        controller.addPlasticRecycling("fff");
        Mockito.verify(view).displayStatus(Mockito.anyString());
    }

    @Test
    public void testAddPlasticRecyclingFailureRange() {
        controller.addPlasticRecycling("10000");
        Mockito.verify(view).displayStatus(Mockito.anyString());
    }

    @Test
    public void testAddPlasticRecyclingSuccess() {
        controller.addPlasticRecycling("1");
        Mockito.verify(view).displayStatus(Mockito.anyString());
    }

}
