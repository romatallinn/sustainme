package model.objects;

public class PaperRecyclingRequest {

    private String uid;
    private float amount;

    public PaperRecyclingRequest(String uid, float amount) {
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
