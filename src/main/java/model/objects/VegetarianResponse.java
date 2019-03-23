package model.objects;

public class VegetarianResponse {

    private int expIncrease;
    private double co2Reduction;
    private int amount;

    public VegetarianResponse(){}

    public VegetarianResponse(int expIncrease, double co2Reduction, int amount) {
        this.expIncrease = expIncrease;
        this.co2Reduction = co2Reduction;
        this.amount = amount;
    }

    public int getExpIncrease() {
        return expIncrease;
    }

    public double getCo2Reduction() {
        return co2Reduction;
    }

    public int getAmount() {
        return amount;
    }
}
