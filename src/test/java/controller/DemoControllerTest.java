package controller;

import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.*;

import org.junit.contrib.java.lang.system.Assertion;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import view.interfaces.IDemoView;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;


public class DemoControllerTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Mock
    private IDemoView view;

    private DemoController controller;

    @Before
    public void setup() {
        controller = new DemoController(view);
    }


    @Test
    public void testSignUpCallbackSuccess() {
        controller.signUpCallback("test@test.com", "123456Aa");
        verify(view).displayData(any());
    }

    @Test
    public void testSignInCallbackSuccess() {
        controller.signInCallback("test@test.com", "123456Aa");
        verify(view).displayData(any());
    }


    @Test
    public void testRetrieveDataSuccess() throws Exception, FirebaseException {

        String path = "test";

        controller.retrieveDataCallback(path);
        verify(view).displayData(any());

    }

    @Test
    public void testPutDataSuccess() throws Exception, FirebaseException, JacksonUtilityException {


        String path = "test";
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("success", true);

        controller.putDataActionCallback(path, data);
        verify(view).displayData(any());

    }

    @Test
    public void testAppShutdown() throws Exception {

        exit.expectSystemExitWithStatus(0);

        controller.applicationShutdown();
        verify(view).displayData(any());

    }




}
