package model.objects;

public class TemperatureRequest {

    private String uid;
    private double temperature;

    public TemperatureRequest(String uid, double temperature) {
        this.uid = uid;
        this.temperature = temperature;
    }

    public String getUid() {
        return uid;
    }

    public double getTemperature() {
        return temperature;
    }
}
