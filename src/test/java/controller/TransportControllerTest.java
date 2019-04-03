package controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import supporting.FirebaseAuth;
import view.interfaces.ITransportView;

public class TransportControllerTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private ITransportView view;

    private TransportController controller;

    @Before
    public void setup() {
        controller = new TransportController(view);
    }

//    @Test
//    public void testUpdate() {
//        controller.updateViewWithData();
//        Mockito.verify(view).updateBikeDistance(Mockito.anyInt());
//        Mockito.verify(view).updatePublicDistance(Mockito.anyInt());
//    }

    @Test
    public void testBikeFailureParse() {
        controller.addBikeKms("fff");
        Mockito.verify(view).displayStatus(Mockito.anyString());
    }

    @Test
    public void testBikeFailureRange() {
        controller.addBikeKms("10000");
        Mockito.verify(view).displayStatus(Mockito.anyString());
    }

//    @Test
//    public void testBikeSuccess() {
//        controller.addBikeKms("1");
//        Mockito.verify(view).displayStatus(Mockito.anyString());
//        Mockito.verify(controller).updateViewWithData();
//    }

    @Test
    public void testTrainFailureParse() {
        controller.addTrainKms("fff");
        Mockito.verify(view).displayStatus(Mockito.anyString());
    }

    @Test
    public void testTrainFailureRange() {
        controller.addTrainKms("10000");
        Mockito.verify(view).displayStatus(Mockito.anyString());
    }

//    @Test
//    public void testTrainSuccess() {
//        controller.addTrainKms("1");
//        Mockito.verify(view).displayStatus(Mockito.anyString());
//        Mockito.verify(controller).updateViewWithData();
//    }

    @Test
    public void testBusFailureParse() {
        controller.addBusKms("fff");
        Mockito.verify(view).displayStatus(Mockito.anyString());
    }

    @Test
    public void testBusFailureRange() {
        controller.addBusKms("10000");
        Mockito.verify(view).displayStatus(Mockito.anyString());
    }

//    @Test
//    public void testBusSuccess() {
//        controller.addBusKms("1");
//        Mockito.verify(view).displayStatus(Mockito.anyString());
//        Mockito.verify(controller).updateViewWithData();
//    }


}
