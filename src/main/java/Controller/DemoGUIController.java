package Controller;

import Model.DemoModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DemoGUIController {

    private DemoModel model;


    @FXML
    private TextField retrievePathField;
    @FXML
    private TextField sendPathField;
    @FXML
    private TextField keyField;
    @FXML
    private TextField valueField;
    @FXML
    private Text textField;

    /**
     * Constructor for the controller class. Instantiates the view and the model.
     */
    public DemoGUIController() {
        try {
            model = new DemoModel();
        } catch (FirebaseException e) {
            textField.setText("Firebase Connection Error:\n" + e.getMessage());
        }

    }


    /**
     * The handler of the request from the view to retrieve the data from the DB and display the result.
     */
    @FXML
    public void RetrieveDataCallback() {
        try {
            String data = model.RetrieveData(retrievePathField.getText());
            textField.setText("\n\n" + data + "\n\n");
        } catch (FirebaseException e) {
            textField.setText("Firebase Exception:\n" + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            textField.setText("Unsupported Encoding Exception:\n" + e.getMessage());
        }

    }

    /**
     * The handler of the request from the view to upload to the DB and display the result.
     *
     * @param path - since DB is NoSQL, it requires the nested path til the object.
     * @param data - map of the key/value pairs (an object) to be inserted into DB.
     */
    @FXML
    public void PutDataActionCallback() {
        try {
            Map<String, Object> dataMap = new LinkedHashMap();

            String path = sendPathField.getText();
            String key = keyField.getText();
            String value = valueField.getText();

            dataMap.put(key, value);
            model.PutData(path, dataMap);
            textField.setText("\nData Uploaded.\n");
        } catch (FirebaseException e) {
            textField.setText("Firebase Exception:\n" + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            textField.setText("Unsupported Encoding Exception:\n" + e.getMessage());
        } catch (JacksonUtilityException e) {
            textField.setText("Jackson Utility Exception:\n" + e.getMessage());
        }
    }

    /**
     * The handler of the request from the view to shutdown the application.
     */
    @FXML
    public void ApplicationShutdown() {
        textField.setText("\nApplication is shutting down...\n");
        System.exit(0);
    }

}