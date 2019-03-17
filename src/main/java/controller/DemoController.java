package controller;


import org.springframework.web.client.RestTemplate;

public class DemoController {
    /**
     * Returns a greeting with an id and a name.
     * @param name - Name to be used.
     * @return - Returns Greeting result.
     */
    public static Greeting getGreeting(String name) {
        final String uri = "http://localhost:8080/greeting?name=" + name;
        RestTemplate restTemplate = new RestTemplate();

        Greeting result = restTemplate.getForObject(uri, Greeting.class);

        return result;
    }
}
