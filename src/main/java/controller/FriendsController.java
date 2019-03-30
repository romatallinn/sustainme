package controller;

import model.FriendsModel;
import model.objects.UserData;
import view.interfaces.IFriendView;

import java.util.List;

public class FriendsController {

    private IFriendView view;
    private FriendsModel model;

    public FriendsController(IFriendView view) {
        this.view = view;
        model = new FriendsModel();
    }

    public void updateViewWithData() {
        List<UserData> friends = model.getFriends();
        // view.updateFriendsList(friends);
    }

    public void addFriendByEmail(String email) {
        model.addFriendByEmail(email);
    }

}
