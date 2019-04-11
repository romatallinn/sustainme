package view.implementation.javafx;

import controller.FriendsController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.objects.UserData;
import view.interfaces.IFriendView;
import view.interfaces.IFriendsComparisonView;

import java.io.IOException;
import java.util.List;


public class JavaFxFriendsView extends JavaFxView implements IFriendView {

    private FriendsController controller;

    @FXML
    private Button homeBtn;

    @FXML
    private TextField addFriendEmail;

    private List<UserData> friends;

    @FXML
    private Button friend1;
    @FXML
    private Button friend2;
    @FXML
    private Button friend3;
    @FXML
    private Button friend4;

    @FXML
    private VBox vboxButtons;

    @Override
    public void initView(FriendsController controller) {
        this.controller = controller;
    }

    @Override
    public void updateFriendsList(List<UserData> friends) {
        this.friends = friends;
        vboxButtons.getChildren().clear();
        for(int i = 0; i < friends.size(); i++) {
            Button btnNumber = new Button();
            btnNumber.setCursor(Cursor.HAND);
            btnNumber.setText(friends.get(i).fname + " " + friends.get(i).lname);
            int finalI = i;
            btnNumber.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    goToComparison(friends.get(finalI));
                }
            });
            vboxButtons.getChildren().add(btnNumber);
        }

//        if (friends.size() > 0) {
//            friend1.setDisable(false);
//            friend1.setCursor(Cursor.HAND);
//            friend1.setText(friends.get(0).fname + " " + friends.get(0).lname);
//            friend1.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//                    goToComparison(friends.get(0));
//                }
//            });
//        } else {
//            friend1.setDisable(true);
//        }
//
//        if (friends.size() > 1) {
//            friend2.setDisable(false);
//            friend2.setCursor(Cursor.HAND);
//            friend2.setText(friends.get(1).fname + " " + friends.get(1).lname);
//            friend2.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//                    goToComparison(friends.get(1));
//                }
//            });
//        } else {
//            friend2.setDisable(true);
//        }
//
//        if (friends.size() > 2) {
//            friend3.setDisable(false);
//            friend3.setCursor(Cursor.HAND);
//            friend3.setText(friends.get(2).fname + " " + friends.get(2).lname);
//            friend3.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//                    goToComparison(friends.get(2));
//                }
//            });
//        } else {
//            friend3.setDisable(true);
//        }
//
//        if (friends.size() > 3) {
//            friend4.setDisable(false);
//            friend4.setCursor(Cursor.HAND);
//            friend4.setText(friends.get(3).fname + " " + friends.get(3).lname);
//            friend4.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//                    goToComparison(friends.get(3));
//                }
//            });
//        } else {
//            friend4.setDisable(true);
//        }

    }

    @Override
    protected void updateLabels() {
        controller.updateViewWithData();
    }

    @Override
    public void displayStatus(String msg) {
        System.out.println(msg);
    }

    private void goToComparison(UserData friend) {

        try {
            switchScene(homeBtn.getScene(), "friendsComparison");
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }

        SceneFx scene = JavaFxApplication.scenes.get("friendsComparison");
        IFriendsComparisonView view = (IFriendsComparisonView)scene.getView();
        view.updateFriendsData(friend);
        view.updateLocalUserData();
    }

    @FXML
    private void goToHome() throws IOException {
        switchScene(homeBtn.getScene(), "home");
    }

    @FXML
    private void addFriendsAction() {
        controller.addFriendByEmail(addFriendEmail.getText());
        addFriendEmail.clear();
        controller.updateViewWithData();
    }


}
