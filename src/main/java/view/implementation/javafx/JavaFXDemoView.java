package view.implementation.javafx;

import controller.DemoController;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import view.interfaces.IDemoView;

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
    public void retrieveDataViewHandler() {
        controller.retrieveDataCallback(retrievePathField.getText());
    }

    @FXML
    public void putDataViewHandler() {

        Map<String, Object> dataMap = new LinkedHashMap();

        String path = sendPathField.getText();

        String key = keyField.getText();
        String value = valueField.getText();

        dataMap.put(key, value);

        controller.putDataActionCallback(path, dataMap);
    }

    @FXML
    public void applicationShutdownHandler() {
        controller.applicationShutdown();
    }

    public void show() { }

    public void displayStatus(String status) {
        textField.setText(status);
    }

    public void displayData(String data) {
        textField.setText(data);
    }

}
