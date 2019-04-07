package model.objects;

public class PlasticRecyclingRequest {

    private String uid;
    private float amount;

    public PlasticRecyclingRequest(String uid, float amount) {
        this.uid = uid;
        this.amount = amount;
    }

    public String getUid() {
        return uid;
    }

    public float getAmount() {
        return amount;
    }
}
