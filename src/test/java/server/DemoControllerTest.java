package server;

import controller.DemoController;
import controller.Greeting;
import org.springframework.context.annotation.ComponentScan;
import server.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DemoControllerTest {

    @Test
    public void getGreetingTest() {
        Greeting greeting = DemoController.getGreeting("Henk");
        assertEquals(greeting.getContent(), "Hello, Henk!");
    }


}
