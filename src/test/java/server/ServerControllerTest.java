package server;

import model.objects.*;
import org.springframework.http.ResponseEntity;
import server.supporting.DatabaseHandler;
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
        assertEquals(40,after.experience - userData.experience);
    }

    @Test
    public void initUserTest() {
        InitRequest initRequest = new InitRequest("initTestUser", "Firstname","Lastname");
        ResponseEntity responseEntity = new ServerController().initUser(initRequest);
        assertNotNull(responseEntity);
    }

    @Test
    public void useBikeTest() throws Exception {
        UserData before = serve.retrieve_user_data("dynamicTestUser");
        int featBef = DatabaseHandler.retrieveFeatureCounter("dynamicTestUser","bike");
        BikeRequest bikeRequest = new BikeRequest("dynamicTestUser", 20);
        serve.useBike(bikeRequest);
        UserData after = serve.retrieve_user_data("dynamicTestUser");
        int featAft = DatabaseHandler.retrieveFeatureCounter("dynamicTestUser","bike");
        assertEquals(20,after.experience - before.experience);
        assertEquals(20,featAft-featBef);
    }

    @Test
    public void usePublicTransportTestBus() throws Exception {
        UserData before = serve.retrieve_user_data("dynamicTestUser");
        int featBef = DatabaseHandler.retrieveFeatureCounter("dynamicTestUser","public");
        PublicTransportRequest ptr = new PublicTransportRequest("dynamicTestUser",20,true);
        serve.usePublicTransport(ptr);
        UserData after = serve.retrieve_user_data("dynamicTestUser");
        int featAft = DatabaseHandler.retrieveFeatureCounter("dynamicTestUser","public");
        assertEquals(20, after.experience - before.experience);
        assertEquals(20, featAft - featBef);
    }

    @Test
    public void usePublicTransportTestRail() throws Exception {
        UserData before = serve.retrieve_user_data("dynamicTestUser");
        int featBef = DatabaseHandler.retrieveFeatureCounter("dynamicTestUser","public");
        PublicTransportRequest ptr = new PublicTransportRequest("dynamicTestUser",20,false);
        serve.usePublicTransport(ptr);
        UserData after = serve.retrieve_user_data("dynamicTestUser");
        int featAft = DatabaseHandler.retrieveFeatureCounter("dynamicTestUser","public");
        assertEquals(7, after.experience - before.experience);
        assertEquals(20, featAft - featBef);
    }

}
