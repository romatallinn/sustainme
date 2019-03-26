package server.supporting;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * The class that initializes the Firebase app connection with certain configuration & keys.
 */
public class FirebaseConnection {

    /**
     * The method that initializes the Firebase app connection with certain configuration & keys.
     */
    public static void initApp() {

        try {

            if (FirebaseApp.getApps().size() == 0) {

                String secretPath = "src/main/java/server/supporting/firebase-secretKey.json";

                FileInputStream serviceAccount =
                        new FileInputStream(secretPath);

                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setDatabaseUrl("https://sustainme-75f00.firebaseio.com/")
                        .build();

                FirebaseApp.initializeApp(options);
                System.out.println("Firebase connection was successfully established!");

            }

            DatabaseHandler.init();

        } catch (IOException e) {

            System.out.println("The server was not launched, because the Firebase's Secret Key "
                    + "was not loaded! Contact Almighty Roman "
                    + "to get this secret thingy, if you yet don't have it!\n"
                    + e.getLocalizedMessage());
            System.exit(1);

        }
    }


}
