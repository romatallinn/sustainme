package View.Implementation.JavaFX;

import View.Interfaces.IDemoView;
import Controller.DemoController;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javafx.fxml.FXML;

import java.util.LinkedHashMap;
import java.util.Map;


public class JavaFXDemoView implements IDemoView {

    private DemoController controller;


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


    public void initView(DemoController controller) {
        this.controller = controller;
    }

    @FXML
    public void RetrieveDataViewHandler() {
        controller.RetrieveDataCallback(retrievePathField.getText());
    }

    @FXML
    public void PutDataViewHandler() {

        Map<String, Object> dataMap = new LinkedHashMap();

        String path = sendPathField.getText();

        String key = keyField.getText();
        String value = valueField.getText();

        dataMap.put(key, value);

        controller.PutDataActionCallback(path, dataMap);
    }

    @FXML
    public void ApplicationShutdownHandler() {
        controller.ApplicationShutdown();
    }

    public void Show() { }

    public void DisplayStatus(String status) {
        textField.setText(status);
    }

    public void DisplayData(String data) {
        textField.setText(data);
    }


}
