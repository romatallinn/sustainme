package model;

import model.objects.FriendRequest;
import model.objects.ShowFriendResponse;
import model.objects.UserData;
import model.objects.UserProfile;
import org.springframework.web.client.RestTemplate;
import supporting.ServerApi;

import java.util.ArrayList;
import java.util.List;

public class FriendsModel {

    private List<UserData> friends = null;

    public List<UserData> getFriends() {

        if (friends == null) {
            loadFriends();
        }

        return friends;

    }

    public void addFriendByEmail(String email) {

        final String uri = ServerApi.HOST + ServerApi.ADD_FRIEND;

        FriendRequest friendRequest =
                new FriendRequest(UserProfile.getInstance().getUid(), email);

        RestTemplate restTemplate = new RestTemplate();
        boolean result =
                restTemplate.postForObject(uri,friendRequest,boolean.class);
        if (!result){
            // TODO: Show on screen.
        }


    }

    public void loadFriends() {

        final String uri = ServerApi.HOST + ServerApi.SHOW_FRIENDS;

        RestTemplate restTemplate = new RestTemplate();
        ShowFriendResponse result = restTemplate.postForObject(uri, UserProfile.getInstance().getUid(), ShowFriendResponse.class);
        List<UserData> endresult = result.getFriends();

        // TODO: Implement Request to Server to retrieve the friends objects from the db.
    }

}
