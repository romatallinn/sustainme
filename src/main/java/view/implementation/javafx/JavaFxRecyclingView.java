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
        System.out.println(msg);
    }

    @Override
    protected void updateLabels() {
        controller.updateViewWithDate();
    }

    @Override
    public void updatePaperRecyclingCounter(float counter) {
        String str = new DecimalFormat("#.##").format(counter);
        paperRecyclingCounter.setText(str);
    }

    @Override
    public void updatePlasticRecyclingCounter(float counter) {
        String str = new DecimalFormat("#.##").format(counter);
        plasticRecyclingCounter.setText(str);
    }

}
