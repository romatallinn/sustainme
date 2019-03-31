package model.objects;

public class BikeRequest {

    private String uid;
    private int distance;

    public BikeRequest(String uid, int distance) {
        this.uid = uid;
        this.distance = distance;
    }

    public String getUid() {
        return uid;
    }

    public int getDistance() {
        return distance;
    }
}
