package server;

import model.objects.InitRequest;
import model.objects.UserData;
import model.objects.VegetarianRequest;
import model.objects.VegetarianResponse;
import org.springframework.http.ResponseEntity;
import server.supporting.FirebaseConnection;

import org.junit.*;
import static org.junit.Assert.*;



public class ServerControllerTest {

    @Before
    public void setup() {
        FirebaseConnection.initApp();
    }
    ServerController serve = new ServerController();

    @Test
    public void retrieve_user_data_Test() throws InterruptedException {
        UserData userData =  serve.retrieve_user_data("staticTestUser");

        assertEquals("staticTestUser", userData.uid);
        assertEquals("Static", userData.fname);
        assertEquals("Testuser", userData.lname);
        assertEquals(0, userData.level);
        assertEquals(34,userData.experience);
        assertEquals(13,userData.co2red,0.0);
    }

    @Test
    public void vegetarianMealTest() throws InterruptedException {
        UserData userData =  serve.retrieve_user_data("dynamicTestUser");
        VegetarianRequest vegetarianRequest = new VegetarianRequest("dynamicTestUser", 2);
        VegetarianResponse vegetarianResponse =  serve.vegetarianMeal(vegetarianRequest);
        UserData after =  serve.retrieve_user_data("dynamicTestUser");
        assertEquals(10,after.experience - userData.experience);
    }

    @Test
    public void initUserTest() {
        InitRequest initRequest = new InitRequest("initTestUser", "Firstname","Lastname");
        ResponseEntity responseEntity = new ServerController().initUser(initRequest);
        assertNotNull(responseEntity);
    }


}
