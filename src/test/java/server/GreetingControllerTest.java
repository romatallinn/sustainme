package server;

import controller.Greeting;
import org.junit.*;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingControllerTest {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getRequestTest() {
        Greeting greeting = restTemplate.getForObject("/greeting?name=Henk", Greeting.class);
        assertEquals(greeting.getContent(), "Hello, Henk!");
    }

    @Test
    public void incrementIdTest() {
        Greeting greeting = restTemplate.getForObject("/greeting?name=Henk", Greeting.class);
        Greeting greeting2 = restTemplate.getForObject("/greeting?name=Henk", Greeting.class);
        assertEquals(greeting2.getId(), 2);
    }

}
