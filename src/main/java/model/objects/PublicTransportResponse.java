package model.objects;

public class PublicTransportResponse {

    private int experience;
    private double co2Reduced;
    private int distance;

    public PublicTransportResponse() {}

    /**
     * The constructor for a public transport response.
     * @param experience new experience of the user
     * @param co2Reduced new co2 reduction of the user
     * @param distance new cycled distance of the user
     */
    public PublicTransportResponse(int experience, double co2Reduced, int distance) {
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
