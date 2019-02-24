package Model;

import SupportingFiles.DatabaseConnection;
import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.model.FirebaseResponse;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * The model class for the basic demonstration purposes of retrieving the data from the DB.
 */
public class DemoModel {

    private DatabaseConnection connection;

    /**
     * Constructor for the model class. Instantiates a local DB connection.
     * TODO: Make a singleton/global db connection instead of local instances for every model.
     * @throws FirebaseException
     */
    public DemoModel() throws FirebaseException
    {
        this.connection = new DatabaseConnection();
    }


    /**
     * Retrieves the entire database content from the root.
     * @return json object of the data retrieved from DB.
     * @throws FirebaseException
     * @throws UnsupportedEncodingException
     */
    public String RetrieveData() throws FirebaseException, UnsupportedEncodingException
    {
        return RetrieveData("");
    }


    /**
     * Retrieves the database content from the given path.
     * @param path - path til the object in the DB.
     * @return json object of the data retrieved from DB.
     * @throws FirebaseException
     * @throws UnsupportedEncodingException
     */
    public String RetrieveData(String path) throws FirebaseException, UnsupportedEncodingException
    {
        return connection.getConnection().get(path).getRawBody();
    }


    /**
     * Uploads an object to the given path with the given key/value pairs.
     * @param path - path to the location where the object shall be inserted.
     * @param data - a map of the key/value pairs (an object) to be inserted.
     * @return FirebaseResponse defining the response to the operation.
     * @throws FirebaseException
     * @throws UnsupportedEncodingException
     * @throws JacksonUtilityException
     */
    public FirebaseResponse PutData(String path, Map<String, Object> data) throws FirebaseException, UnsupportedEncodingException, JacksonUtilityException {

        return connection.getConnection().put(path, data);

    }

}
