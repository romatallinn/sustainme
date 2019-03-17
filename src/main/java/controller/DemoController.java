package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class DemoController {

    public static Greeting getGreeting(String name){
        final String uri = "http://localhost:8080/greeting?name=" + name;
        RestTemplate restTemplate = new RestTemplate();

        Greeting result = restTemplate.getForObject(uri, Greeting.class);

        return result;
    }
}
