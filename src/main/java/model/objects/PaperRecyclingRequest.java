package model.objects;

public class PaperRecyclingRequest {

    private String uid;
    private double amount;

    public PaperRecyclingRequest(String uid, double amount) {
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
