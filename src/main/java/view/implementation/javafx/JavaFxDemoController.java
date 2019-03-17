package view.implementation.javafx;


import controller.DemoController;
import controller.Greeting;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class JavaFxDemoController {

    @FXML
    TextField name;
    @FXML
    Text textField;

    public void sendName(){
        Greeting greeting = DemoController.getGreeting(name.getText());
        textField.setText(greeting.getContent());
    }
}
