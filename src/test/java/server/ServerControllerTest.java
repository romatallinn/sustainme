package server;

import model.objects.*;
import org.springframework.http.ResponseEntity;
import server.supporting.DatabaseHandler;
import server.supporting.FirebaseConnection;

import org.junit.*;

import java.util.concurrent.TimeUnit;

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
        int featBef = DatabaseHandler.retrieveFeatureCounter("dynamicTestUser","vegmeals");
        VegetarianRequest vegetarianRequest = new VegetarianRequest("dynamicTestUser", 2);
        serve.vegetarianMeal(vegetarianRequest);
        int featAft = DatabaseHandler.retrieveFeatureCounter("dynamicTestUser","vegmeals");
        UserData after =  serve.retrieve_user_data("dynamicTestUser");
        assertEquals(40,after.experience - userData.experience);
        assertEquals(2,featAft - featBef);
    }

    @Test
    public void vegMealBelowZeroTest() throws InterruptedException {
        UserData userData =  serve.retrieve_user_data("dynamicTestUser");
        int featBef = DatabaseHandler.retrieveFeatureCounter("dynamicTestUser","vegmeals");
        VegetarianRequest vegetarianRequest = new VegetarianRequest("dynamicTestUser", -1);
        serve.vegetarianMeal(vegetarianRequest);
        int featAft = DatabaseHandler.retrieveFeatureCounter("dynamicTestUser","vegmeals");
        UserData after =  serve.retrieve_user_data("dynamicTestUser");
        assertEquals(0,after.experience - userData.experience);
        assertEquals(0,featAft - featBef);
    }

    @Test
    public void vegMealAboveThreeTest() throws InterruptedException {
        UserData userData =  serve.retrieve_user_data("dynamicTestUser");
        int featBef = DatabaseHandler.retrieveFeatureCounter("dynamicTestUser","vegmeals");
        VegetarianRequest vegetarianRequest = new VegetarianRequest("dynamicTestUser", 7);
        serve.vegetarianMeal(vegetarianRequest);
        int featAft = DatabaseHandler.retrieveFeatureCounter("dynamicTestUser","vegmeals");
        UserData after =  serve.retrieve_user_data("dynamicTestUser");
        assertEquals(0,after.experience - userData.experience);
        assertEquals(0,featAft - featBef);
    }

    @Test
    public void initUserTest() {
        InitRequest initRequest = new InitRequest("initTestUser", "Firstname","Lastname");
        ResponseEntity responseEntity = new ServerController().initUser(initRequest);
        assertNotNull(responseEntity);
    }

    @Test
    public void useBikeTest() throws Exception {
        TimeUnit.SECONDS.sleep(5);
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
        TimeUnit.SECONDS.sleep(5);
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
        TimeUnit.SECONDS.sleep(5);
        UserData before = serve.retrieve_user_data("dynamicTestUser");
        int featBef = DatabaseHandler.retrieveFeatureCounter("dynamicTestUser","public");
        PublicTransportRequest ptr = new PublicTransportRequest("dynamicTestUser",20,false);
        serve.usePublicTransport(ptr);
        UserData after = serve.retrieve_user_data("dynamicTestUser");
        int featAft = DatabaseHandler.retrieveFeatureCounter("dynamicTestUser","public");
        assertEquals(20, after.experience - before.experience);
        assertEquals(20, featAft - featBef);
    }

    @Test
    public void localProduceTest() throws Exception {
        UserData before = serve.retrieve_user_data("dynamicTestUser");
        double featBef = DatabaseHandler.retrieveDoubleFeatureCounter("dynamicTestUser","localproduce");
        LocalProduceRequest lpr = new LocalProduceRequest("dynamicTestUser", 2);
        serve.localProduce(lpr);
        UserData after = serve.retrieve_user_data("dynamicTestUser");
        double featAft = DatabaseHandler.retrieveFeatureCounter("dynamicTestUser","localproduce");
        assertEquals(2, after.experience - before.experience);
        assertEquals(2, featAft - featBef,0.0);
    }

    @Test
    public void paperRecyclingTest() throws Exception {
        UserData before = serve.retrieve_user_data("dynamicTestUser");
        double featBef = DatabaseHandler.retrieveDoubleFeatureCounter("dynamicTestUser","paperrecycling");
        PaperRecyclingRequest lpr = new PaperRecyclingRequest("dynamicTestUser", 2);
        serve.paperRecycling(lpr);
        UserData after = serve.retrieve_user_data("dynamicTestUser");
        double featAft = DatabaseHandler.retrieveFeatureCounter("dynamicTestUser","paperrecycling");
        assertEquals(16, after.experience - before.experience);
        assertEquals(2, featAft - featBef,0.0);
    }

    @Test
    public void plasticRecyclingTest() throws Exception {
        UserData before = serve.retrieve_user_data("dynamicTestUser");
        double featBef = DatabaseHandler.retrieveDoubleFeatureCounter("dynamicTestUser","plasticrecycling");
        PlasticRecyclingRequest lpr = new PlasticRecyclingRequest("dynamicTestUser", 2);
        serve.plasticRecycling(lpr);
        UserData after = serve.retrieve_user_data("dynamicTestUser");
        double featAft = DatabaseHandler.retrieveFeatureCounter("dynamicTestUser","plasticrecycling");
        assertEquals(80, after.experience - before.experience);
        assertEquals(2, featAft - featBef,0.0);
    }

}
