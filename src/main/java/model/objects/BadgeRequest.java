package model.objects;

public class BadgeRequest {

    private String uid;
    private String badges;

    public BadgeRequest(String uid, String badges) {
        this.uid = uid;
        this.badges = badges;
    }

    public String getUid() {
        return uid;
    }

    public String getBadges() {
        return badges;
    }
}
