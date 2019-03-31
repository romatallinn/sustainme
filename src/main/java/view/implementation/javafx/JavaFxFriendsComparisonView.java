package view.implementation.javafx;

import controller.FriendsComparisonController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.objects.UserData;
import view.interfaces.IFriendsComparisonView;

import java.io.IOException;


public class JavaFxFriendsComparisonView extends JavaFxView implements IFriendsComparisonView {

    private FriendsComparisonController controller;

    @FXML
    private Button homeBtn;

    @Override
    public void initView(FriendsComparisonController controller) {
        this.controller = controller;
    }

    @FXML
    private void goToHome() throws IOException {
        switchScene(homeBtn.getScene(), "home");
    }

    @Override
    public void updateFriendsData(UserData friend) {

    }
}
