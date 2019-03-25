package model.objects;

public class VegetarianRequest {

    private String uid;
    private int amount;

    public VegetarianRequest(String uid, int amount) {
        this.uid = uid;
        this.amount = amount;
    }

    public String getUid() {
        return uid;
    }

    public int getAmount() {
        return amount;
    }
}
