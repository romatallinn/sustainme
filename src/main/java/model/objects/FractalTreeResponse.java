package model.objects;

public class FractalTreeResponse {

    private double bikeCo2;
    private double vegmealsCO2;
    private double localproduceCO2;
    private double publicCO2;

    /**
     * Constructor for a fractalTree response.
     *
     * @param bikeCo2         - new co2 reduction for bike
     * @param vegmealsCO2     - new co2 reduction vor vegetarian meals
     * @param localproduceCO2 - new co2 reduction for local produce
     * @param publicCO2       - new co2 reduction for public transport
     */
    public FractalTreeResponse(
        double bikeCo2,
        double vegmealsCO2,
        double localproduceCO2,
        double publicCO2) {
        this.bikeCo2 = bikeCo2;
        this.localproduceCO2 = localproduceCO2;
        this.vegmealsCO2 = vegmealsCO2;
        this.publicCO2 = publicCO2;
    }

    public double getBikeCo2() {
        return bikeCo2;
    }

    public double getLocalproduceCO2() {
        return localproduceCO2;
    }

    public double getVegmealsCO2() {
        return vegmealsCO2;
    }

    public double getPublicCO2() {
        return publicCO2;
    }
}
