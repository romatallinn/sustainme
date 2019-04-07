package view.implementation.javafx;

import controller.PaperRecyclingController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import view.interfaces.IPaperRecyclingView;

import java.io.IOException;
import java.text.DecimalFormat;

public class JavaFxPaperRecyclingView extends JavaFxView implements IPaperRecyclingView {

    private PaperRecyclingController controller;

    @FXML
    private Button homeBtn;

    @FXML
    private TextField paperRecyclingCountInput;

    @FXML
    private Text paperRecyclingCounter;

    @Override
    public void initView(PaperRecyclingController controller) {
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

    @Override
    public void displayStatus(String msg) {
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

}
