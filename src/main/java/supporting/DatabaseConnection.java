package supporting;

import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.service.Firebase;

public class DatabaseConnection {

    private Firebase connection;


    /**
     * Constructor for the class; initializes the connection between the client
     * and the Firebase service for URL defined in the configuration file.
     * @throws FirebaseException - database connection exception.
     */
    public DatabaseConnection() throws FirebaseException {
        connection = new Firebase(AppConfig.dbUrl);
    }

    /**
     * Getter for the connection between the client and Firebase service.
     * @return Firebase connection
     */
    public Firebase getConnection() {
        return connection;
    }

}
