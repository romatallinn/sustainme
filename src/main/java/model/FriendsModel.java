package model;

import model.objects.UserData;

import java.util.List;

public class FriendsModel {

    private List<UserData> friends = null;

    public List<UserData> getFriends() {

        if (friends == null) {
            loadFriends();
        }

        return friends;

    }

    public void loadFriends() {
        // TODO: Implement Request to Server to retrieve the friends objects from the db.
    }

}
