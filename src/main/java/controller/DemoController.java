package controller;


import org.springframework.web.client.RestTemplate;

public class DemoController {

    /**
     * Sends a get request to server for a greeting.
     * @param name name for greeting the server.
     * @return greeting
     */
    public static Greeting getGreeting(String name) {
        final String uri = "http://localhost:8080/greeting?name=" + name;
        RestTemplate restTemplate = new RestTemplate();

        Greeting result = restTemplate.getForObject(uri, Greeting.class);

        return result;
    }
}
