package Controller;

import Model.DemoModel;
import View.TerminalView;

import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * The controller class for the terminal demonstration purposes.
 * Handles basic model and view to demonstrate the functionality of the Firebase's Realtime Database.
 */
public class DemoTerminalController {

    private DemoModel model;
    private TerminalView view;

    /**
     * Constructor for the controller class. Instantiates the view and the model.
     */
    public DemoTerminalController()
    {
        view = new TerminalView(this);

        try {
            model = new DemoModel();
        } catch(FirebaseException e)
        {
            view.DisplayErrorMessage("Firebase Connection Error:\n" + e.getMessage());
        }

        view.InitializeMenuInteraction();
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
            view.DisplayErrorMessage("Firebase Exception:\n" + e.getMessage());
        }
        catch (UnsupportedEncodingException e) {
            view.DisplayErrorMessage("Unsupported Encoding Exception:\n" + e.getMessage());
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
            view.DisplayErrorMessage("Firebase Exception:\n" + e.getMessage());
        }
        catch (UnsupportedEncodingException e) {
            view.DisplayErrorMessage("Unsupported Encoding Exception:\n" + e.getMessage());
        }
        catch (JacksonUtilityException e) {
            view.DisplayErrorMessage("Jackson Utility Exception:\n" + e.getMessage());
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
