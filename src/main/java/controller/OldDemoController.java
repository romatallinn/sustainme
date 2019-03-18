package controller;

import model.DemoModel;
import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.model.FirebaseResponse;
import supporting.AuthService;
import view.interfaces.IDemoView;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class OldDemoController {


    /**
     * The model used by the controller.
     */
    private DemoModel model;

    /**
     * The view interface used by the controller.
     */
    private IDemoView view;

    /**
     * Constructor for the controller class. Instantiates the view and the model.
     */
    public OldDemoController(IDemoView view) {

        this.view = view;

        // Instantiate the model
        try {
            model = new DemoModel();
        } catch (FirebaseException e) {
            view.displayStatus("Firebase Connection Error:\n" + e.getMessage());
        }

    }

    /**
     * Sign Up callback method; requested from the view.
     * @param email - user's email to be used for registration.
     * @param pass - user's password to be used for registration.
     */
    public void signUpCallback(String email, String pass) {

        try {
            FirebaseResponse response = AuthService.signUp(email, pass);
            view.displayData("\n\n" + response.getRawBody() + "\n\n");
        } catch (FirebaseException | UnsupportedEncodingException | JacksonUtilityException e) {
            view.displayStatus("Exception:\n" + e.getMessage());
        }

    }

    /**
     * Sign In callback method; requested from the view.
     * @param email - user's email to be used for goToSignin.
     * @param pass - user's password to be used for goToSignin.
     */
    public void signInCallback(String email, String pass) {

        try {
            FirebaseResponse response = AuthService.signIn(email, pass);
            view.displayData("\n\n" + response.getRawBody() + "\n\n");
        } catch (FirebaseException | UnsupportedEncodingException | JacksonUtilityException e) {
            view.displayStatus("Exception:\n" + e.getMessage());
        }

    }

    /**
     * The handler of the request from the view to retrieve
     * the data from the DB and display the result.
     */
    public void retrieveDataCallback(String path) {
        try {
            String data = model.retrieveData(path);
            view.displayData("\n\n" + data + "\n\n");
        } catch (FirebaseException | UnsupportedEncodingException e) {
            view.displayStatus("Exception:\n" + e.getMessage());
        }

    }

    /**
     * The handler of the request from the view to upload to the DB and display the result.
     * @param path - since DB is NoSQL, it requires the nested path til the object.
     * @param data - map of the key/value pairs (an object) to be inserted into DB.
     */
    public void putDataActionCallback(String path, Map<String, Object> data) {
        try {
            model.putData(path, data);
            view.displayData("\nData Uploaded.\n");
        } catch (FirebaseException | UnsupportedEncodingException | JacksonUtilityException e) {
            view.displayStatus("Exception:\n" + e.getMessage());
        }
    }

    /**
     * The handler of the request from the view to shutdown the application.
     */
    public void applicationShutdown() {
        view.displayData("\nApplication is shutting down...\n");
        System.exit(0);
    }

}
