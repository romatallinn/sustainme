package controller;

import net.thegreshams.firebase4j.error.FirebaseException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.*;

import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import supporting.DatabaseConnection;
import view.interfaces.IDemoView;

import java.util.LinkedHashMap;
import java.util.Map;


public class OldDemoControllerTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Mock
    private IDemoView view;

    private OldDemoController controller;

    @Before
    public void setup() throws FirebaseException {
        DatabaseConnection.init(null);
        controller = new OldDemoController(view);
    }

    @Test
    public void testSignInCallbackSuccess() {
        controller.signInCallback("test@test.com", "123456Aa");
        verify(view).displayData(any());
    }

    @Test
    public void testSignUpCallbackSuccess() {
        controller.signUpCallback("test@test.com", "123456Aa");
        verify(view).displayData(any());
    }


    @Test
    public void testRetrieveDataSuccess() {

        String path = "test";

        controller.retrieveDataCallback(path);
        verify(view).displayData(any());

    }

    @Test
    public void testPutDataSuccess() {


        String path = "test";
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("success", true);

        controller.putDataActionCallback(path, data);
        verify(view).displayData(any());

    }

    @Test
    public void testAppShutdown() {

        exit.expectSystemExitWithStatus(0);

        controller.applicationShutdown();
        verify(view).displayData(any());

    }
}
