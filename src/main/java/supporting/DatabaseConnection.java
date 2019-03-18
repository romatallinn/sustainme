package supporting;

import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.service.Firebase;

public class DatabaseConnection {

    private static Firebase instance;

    /**
     * Constructor for the class; initializes the connection between the client
     * and the Firebase service for URL defined in the configuration file.
     * @throws FirebaseException - database connection exception.
     */
    public DatabaseConnection(String token) throws FirebaseException {
        instance = new Firebase(AppConfig.dbUrl, token);
    }

    /**
     * Initializes a connection with Firebase.
     * @param token - auth token used by Firebase to verify user.
     * @return Firebase connection.
     * @throws FirebaseException - database connection exception.
     */
    public static Firebase init(String token) throws FirebaseException {

        if (instance == null) {
            new DatabaseConnection(token);
        }

        return instance;
    }

    public static Firebase getInstance() throws FirebaseException {
        return instance;
    }

    public static void killConnection() {
        instance = null;
    }


}
