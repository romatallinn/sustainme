package model.objects;

import java.util.List;

public class ShowFriendResponse {

    private List<UserData> friends;

    public ShowFriendResponse() { }

    public ShowFriendResponse(List<UserData> friends) {
        this.friends = friends;
    }

    public List<UserData> getFriends() {
        return friends;
    }
}
