package model;

import model.objects.FriendRequest;
import model.objects.ShowFriendResponse;
import model.objects.UserData;
import org.springframework.web.client.RestTemplate;
import supporting.ServerApi;

import java.util.ArrayList;
import java.util.List;

public class FriendsModel {

    private List<UserData> friends = null;

    /**
     * Getter for the stored list of friends.
     * @return list of all friends that user has.
     */
    public List<UserData> getFriends() {

        if (friends == null) {

            if (UserProfile.getInstance().authToken.isEmpty()) {
                friends = new ArrayList<>();
            } else {
                loadFriends();
            }

        }

        return friends;

    }

    /**
     * Add new friend by email address.
     * @param email address of the friend to be added.
     */
    public boolean addFriendByEmail(String email) {

        if (UserProfile.getInstance().authToken.isEmpty()) {
            return false;
        }

        final String uri = ServerApi.HOST + ServerApi.ADD_FRIEND;

        FriendRequest friendRequest =
                new FriendRequest(UserProfile.getInstance().getUid(), email);

        RestTemplate restTemplate = new RestTemplate();
        boolean result =
                restTemplate.postForObject(uri, friendRequest, boolean.class);
        return result;
    }

    /**
     * Update the friend's list by requesting the server.
     */
    public void loadFriends() {

        if (UserProfile.getInstance().authToken.isEmpty()) {
            return;
        }

        final String uri = ServerApi.HOST + ServerApi.SHOW_FRIENDS;

        RestTemplate restTemplate = new RestTemplate();

        ShowFriendResponse result = restTemplate.postForObject(uri,
                UserProfile.getInstance().getUid(), ShowFriendResponse.class);

        friends = result.getFriends();

    }

}
