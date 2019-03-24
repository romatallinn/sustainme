package server;

import model.objects.InitRequest;
import model.objects.UserData;
import model.objects.VegetarianRequest;
import model.objects.VegetarianResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import supporting.ServerApi;
import server.supporting.FirebaseConnection;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;


public class ServerControllerTest {

    @Before
    public void setup() {
        FirebaseConnection.initApp();
    }






    @Test
    public void retrieve_user_data_Test() throws InterruptedException {
        UserData userData =  new ServerController().retrieve_user_data("5An25iOCs5bisQ2ORzaaoUD9nNo2");

        assertEquals("5An25iOCs5bisQ2ORzaaoUD9nNo2", userData.uid);
        assertEquals("Firstname", userData.fname);
        assertEquals("Lastname", userData.lname);
        assertNotNull(userData.level);
        assertNotNull(userData.experience);
        assertNotNull(userData.vegmeals);
    }

    @Test
    public void vegetarianMealTest() throws InterruptedException {
        UserData userData =  new ServerController().retrieve_user_data("5An25iOCs5bisQ2ORzaaoUD9nNo2");
        VegetarianRequest vegetarianRequest = new VegetarianRequest("5An25iOCs5bisQ2ORzaaoUD9nNo2", 2);
        VegetarianResponse vegetarianResponse =  new ServerController().vegetarianMeal(vegetarianRequest);
        UserData after =  new ServerController().retrieve_user_data("5An25iOCs5bisQ2ORzaaoUD9nNo2");
        assertEquals(10,after.experience - userData.experience);
    }

    @Test
    public void initUserTest() {
        InitRequest initRequest = new InitRequest("Test", "Firstname","Lastname");
        ResponseEntity responseEntity = new ServerController().initUser(initRequest);
        assertNotNull(responseEntity);
    }


}
