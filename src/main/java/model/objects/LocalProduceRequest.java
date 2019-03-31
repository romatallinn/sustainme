package model.objects;

public class LocalProduceRequest {

    private String uid;
    private double weight;

    public LocalProduceRequest(String uid, double weight) {
        this.uid = uid;
        this.weight = weight;
    }

    public String getUid() {
        return uid;
    }

    public double getWeight() {
        return weight;
    }
}