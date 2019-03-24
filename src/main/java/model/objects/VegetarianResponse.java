package model.objects;

public class VegetarianResponse {

    private int experience;
    private double co2Reduced;
    private int amount;

    public VegetarianResponse(){}

    /**
     * The constructor for a vegetarian response.
     * @param experience new experience of the user
     * @param co2Reduced new co2 reduction of the user
     * @param amount new amount of vegetarian meals of the user
     */
    public VegetarianResponse(int experience, double co2Reduced, int amount) {
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

    public int getAmount() {
        return amount;
    }
}
