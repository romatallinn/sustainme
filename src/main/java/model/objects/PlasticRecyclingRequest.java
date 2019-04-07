package model.objects;

public class PlasticRecyclingRequest {

    private String uid;
    private double amount;

    public PlasticRecyclingRequest(String uid, double amount) {
        this.uid = uid;
        this.amount = amount;
    }

    public String getUid() {
        return uid;
    }

    public double getAmount() {
        return amount;
    }
}
