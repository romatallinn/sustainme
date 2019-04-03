package model.objects;

public class PaperRecyclingResponse {

    private int experience;
    private double co2Reduced;
    private float amount;

    public PaperRecyclingResponse() {
    }

    /**
     * The constructor for a PaperRecycling response.
     * @param experience - new experience of the user
     * @param co2Reduced - new co2 reduction of the user
     * @param amount     - new amount of paper recycling of the user
     */
    public PaperRecyclingResponse(int experience, double co2Reduced, float amount) {
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
