package model.objects;

public class PublicTransportRequest {

    private String uid;
    private int distance;
    private boolean type;

    /**
     * Constructor for a public transport request.
     * @param uid the user id of the user
     * @param distance the distance traveled by public transport
     * @param type the type of public transport taken
     */
    public PublicTransportRequest(String uid, int distance, boolean type) {
        this.uid = uid;
        this.distance = distance;
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public int getDistance() {
        return distance;
    }

    public boolean getType() {
        return type;
    }
}
