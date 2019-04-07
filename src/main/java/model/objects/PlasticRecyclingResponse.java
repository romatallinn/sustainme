package model.objects;

public class PlasticRecyclingResponse {

    private int experience;
    private double co2Reduced;
    private float amount;

    public PlasticRecyclingResponse() {
    }

    /**
     * The constructor for a PlasticRecycling response.
     * @param experience - new experience of the user
     * @param co2Reduced - new co2 reduction of the user
     * @param amount     - new amount of plastic recycling of the user
     */
    public PlasticRecyclingResponse(int experience, double co2Reduced, float amount) {
        this.experience = experience;
        this.co2Reduced = co2Reduced;
        this.amount = amount;
    }

    public int getExperience() {
        return experience;
    }

    public double getCo2Reduced() {
        return co2Reduced;
    }

    public float getAmount() {
        return amount;
    }
}
