package model.objects;

public class FractalTreeRequest {

    private String uid;
    private double bikeCo2;
    private double vegmealsCO2;
    private double localproduceCO2;
    private double publicCO2;

    /**
     * Constructor for FractalTreeRequest.
     * @param uid               - user identification
     * @param bikeCo2           - reduced co2 for bike
     * @param vegmealsCO2       - reduced co2 for vegetarian meals
     * @param localproduceCO2   - reduced co2 local produce
     * @param publicCO2         - reduced co2 public transport
     */
    public FractalTreeRequest(
        String uid,
        double bikeCo2,
        double vegmealsCO2,
        double localproduceCO2,
        double publicCO2) {
        this.uid = uid;
        this.bikeCo2 = bikeCo2;
        this.localproduceCO2 = localproduceCO2;
        this.vegmealsCO2 = vegmealsCO2;
        this.publicCO2 = publicCO2;
    }

    public String getUid() {
        return uid;
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

