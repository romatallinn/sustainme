package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import server.supporting.FirebaseConnection;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        FirebaseConnection.initApp();
        SpringApplication.run(Application.class, args);
    }
}