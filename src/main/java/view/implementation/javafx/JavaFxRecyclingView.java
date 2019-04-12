package view.implementation.javafx;

import controller.RecyclingController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import view.interfaces.IRecyclingView;

import java.io.IOException;
import java.text.DecimalFormat;

public class JavaFxRecyclingView extends JavaFxView implements IRecyclingView {

    private RecyclingController controller;

    @FXML
    private Button homeBtn;

    @FXML
    private TextField paperRecyclingCountInput;

    @FXML
    private Text paperRecyclingCounter;

    @FXML
    private TextField plasticRecyclingCountInput;

    @FXML
    private Text plasticRecyclingCounter;

    @FXML
    private Text statusMsg;

    @Override
    public void initView(RecyclingController controller) {
        this.controller = controller;
    }

    @FXML
    private void goToHome() throws IOException {
        switchScene(homeBtn.getScene(), "home");
    }

    @FXML
    private void addPaperRecycling() {
        controller.addPaperRecycling(paperRecyclingCountInput.getText());
        paperRecyclingCountInput.clear();
    }

    @FXML
    private void addPlasticRecycling() {
        controller.addPlasticRecycling(plasticRecyclingCountInput.getText());
        plasticRecyclingCountInput.clear();
    }

    @Override
    public void displayStatus(String msg) {
        statusMsg.setText(msg);
        System.out.println(msg);
    }

    @Override
    protected void updateLabels() {
        controller.updateViewWithDate();
    }

    @Override
    public void updatePaperRecyclingCounter(double counter) {
        String str = new DecimalFormat("#.##").format(counter);
        paperRecyclingCounter.setText(str);
    }

    @Override
    public void updatePlasticRecyclingCounter(double counter) {
        String str = new DecimalFormat("#.##").format(counter);
        plasticRecyclingCounter.setText(str);
    }

}
