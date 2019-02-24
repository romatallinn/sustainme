package SupportingFiles;

import SupportingFiles.AppConfig;
import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.service.Firebase;

public class DatabaseConnection {

    private Firebase connection;

    /**
     * Getter for the connection between the client and Firebase service.
     * @return Firebase connection
     */
    public Firebase getConnection()
    {
        return connection;
    }

    /**
     * Constructor for the class; initializes the connection between the client and the Firebase service for URL defined in the configuration file.
     * @throws FirebaseException
     */
    public DatabaseConnection() throws FirebaseException
    {
        connection = new Firebase(AppConfig.dbUrl);
    }



}
