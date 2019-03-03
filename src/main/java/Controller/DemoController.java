package Controller;

import Model.DemoModel;

import SupportingFiles.AuthService;
import View.Interfaces.IDemoView;

import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.model.FirebaseResponse;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * The controller class for the terminal demonstration purposes.
 * Handles basic model and view to demonstrate the functionality of the Firebase's Realtime Database.
 */
public class DemoController {

    /**
     * The model used by the controller
     */
    private DemoModel model;

    /**
     * The view interface used by the controller
     */
    private IDemoView view;

    /**
     * Constructor for the controller class. Instantiates the view and the model.
     */
    public DemoController(IDemoView view)
    {

        this.view = view;


        // Instantiate the model
        try {
            model = new DemoModel();
        } catch(FirebaseException e)
        {
            view.DisplayStatus("Firebase Connection Error:\n" + e.getMessage());
        }

    }


    public void SignUpCallback(String email, String pass) {

        try {
            FirebaseResponse response = AuthService.SignUp(email, pass);
            view.DisplayData("\n\n" + response.getRawBody() + "\n\n");
        }
        catch (FirebaseException e) {
            view.DisplayStatus("Firebase Exception:\n" + e.getMessage());
        }
        catch (UnsupportedEncodingException e) {
            view.DisplayStatus("Unsupported Encoding Exception:\n" + e.getMessage());
        }
        catch (JacksonUtilityException e) {
            view.DisplayStatus("Jackson Utility Exception:\n" + e.getMessage());
        }

    }

    public void SignInCallback(String email, String pass) {

        try {
            FirebaseResponse response = AuthService.SignIn(email, pass);
            view.DisplayData("\n\n" + response.getRawBody() + "\n\n");
        }
        catch (FirebaseException e) {
            view.DisplayStatus("Firebase Exception:\n" + e.getMessage());
        }
        catch (UnsupportedEncodingException e) {
            view.DisplayStatus("Unsupported Encoding Exception:\n" + e.getMessage());
        }
        catch (JacksonUtilityException e) {
            view.DisplayStatus("Jackson Utility Exception:\n" + e.getMessage());
        }

    }

    /**
     * The handler of the request from the view to retrieve the data from the DB and display the result.
     */
    public void RetrieveDataCallback(String path)
    {
        try {
            String data = model.RetrieveData(path);
            view.DisplayData("\n\n" + data + "\n\n");
        }
        catch (FirebaseException e) {
            view.DisplayStatus("Firebase Exception:\n" + e.getMessage());
        }
        catch (UnsupportedEncodingException e) {
            view.DisplayStatus("Unsupported Encoding Exception:\n" + e.getMessage());
        }

    }

    /**
     * The handler of the request from the view to upload to the DB and display the result.
     * @param path - since DB is NoSQL, it requires the nested path til the object.
     * @param data - map of the key/value pairs (an object) to be inserted into DB.
     */
    public void PutDataActionCallback(String path, Map<String, Object> data)
    {
        try {
            model.PutData(path, data);
            view.DisplayData("\nData Uploaded.\n");
        }
        catch (FirebaseException e) {
            view.DisplayStatus("Firebase Exception:\n" + e.getMessage());
        }
        catch (UnsupportedEncodingException e) {
            view.DisplayStatus("Unsupported Encoding Exception:\n" + e.getMessage());
        }
        catch (JacksonUtilityException e) {
            view.DisplayStatus("Jackson Utility Exception:\n" + e.getMessage());
        }
    }

    /**
     * The handler of the request from the view to shutdown the application.
     */
    public void ApplicationShutdown() {
        view.DisplayData("\nApplication is shutting down...\n");
        System.exit(0);
    }


}
