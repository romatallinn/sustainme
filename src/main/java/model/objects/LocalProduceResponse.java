package model.objects;

public class LocalProduceResponse {

    private int experience;
    private double co2Reduced;
    private double amount;

    public LocalProduceResponse(){}

    /**
     * The constructor for a vegetarian response.
     * @param experience new experience of the user
     * @param co2Reduced new co2 reduction of the user
     * @param amount new amount of vegetarian meals of the user
     */
    public LocalProduceResponse(int experience, double co2Reduced, double amount) {
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

    public double getAmount() {
        return amount;
    }
}
