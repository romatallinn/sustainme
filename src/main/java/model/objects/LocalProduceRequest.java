package model.objects;

public class LocalProduceRequest {

    private String uid;
    private float weight;

    public LocalProduceRequest(String uid, float weight) {
        this.uid = uid;
        this.weight = weight;
    }

    public String getUid() {
        return uid;
    }

    public float getWeight() {
        return weight;
    }
}