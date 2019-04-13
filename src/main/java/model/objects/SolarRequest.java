package model.objects;

public class SolarRequest {

    private String uid;
    private int addArea;

    public SolarRequest(String uid, int addArea) {
        this.uid = uid;
        this.addArea = addArea;
    }

    public String getUid() {
        return uid;
    }

    public int getAddArea() {
        return addArea;
    }
}
