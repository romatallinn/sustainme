package model.objects;

public class BikeResponse {

    private int experience;
    private double co2Reduced;
    private int distance;

    public BikeResponse() {}

    public BikeResponse(int experience, double co2Reduced, int distance) {
        this.experience = experience;
        this.co2Reduced = co2Reduced;
        this.distance = distance;
    }

    public int getExperience() {
        return experience;
    }

    public double getCo2Reduced() {
        return co2Reduced;
    }

    public int getDistance() {
        return distance;
    }
}
