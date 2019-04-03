package model.objects;

public class FriendRequest {

    private String uid;
    private String friendEmail;

    public FriendRequest(String uid, String friendEmail) {
        this.uid = uid;
        this.friendEmail = friendEmail;
    }

    public String getUid() {
        return uid;
    }

    public String getFriendEmail() {
        return friendEmail;
    }
}
