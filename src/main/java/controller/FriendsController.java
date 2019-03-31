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

    /**
     * Updates the view elements with fresh data.
     */
    public void updateViewWithData() {
        model.loadFriends();
        List<UserData> friends = model.getFriends();
        view.updateFriendsList(friends);
    }

    /**
     * Adds a new friend by given email address.
     * @param email address of the friend to be added.
     */
    public void addFriendByEmail(String email) {
        model.addFriendByEmail(email);
        view.displayStatus("Friend was added!");
    }

}
