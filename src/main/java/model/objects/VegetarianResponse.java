package model.objects;

public class VegetarianResponse {

    private int experience;
    private double co2Reduced;
    private int amount;

    public VegetarianResponse(){}

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
