package api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.ByteArrayInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ URL.class, HttpURLConnection.class, ApiRequest.class})
public class ApiRequestTest {

    private String string = "<result_motor_vehicles_direct>2.5</result_motor_vehicles_direct>" +
            "<result_publictrans_direct>1.35</result_publictrans_direct>";


    @Before
    public void setup() throws Exception {
        URL u = PowerMockito.mock(URL.class);
        PowerMockito.whenNew(URL.class).withAnyArguments().thenReturn(u);
        HttpURLConnection huc = PowerMockito.mock(HttpURLConnection.class);
        PowerMockito.when(u.openConnection()).thenReturn(huc);
        PowerMockito.when(huc.getInputStream()).thenReturn(new ByteArrayInputStream(string.getBytes()));
    }

    @Test
    public void requestBikeTest() throws Exception {
        double result = ApiRequest.requestBike("5");
        assertEquals(result, 2.5, 0.0001);
    }

    @Test
    public void requestPublicTransTest() throws Exception {
        double result = ApiRequest.requestPublicTrans("2", true);
        assertEquals(result, 1.15, 0.0001);
    }
}
