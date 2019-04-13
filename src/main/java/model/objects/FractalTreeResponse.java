package model.objects;

public class FractalTreeResponse {

    private double bikeCo2;
    private double vegmealsCO2;
    private double localproduceCO2;
    private double publicCO2;
    private double plasticrecyclingCO2;
    private double paperrecyclingCO2;
    private double solarareaCO2;
    private double temperatureCO2;

    public FractalTreeResponse(){
        
    }

    /**
     * Constructor for a fractalTree response.
     *
     * @param bikeCo2         - new co2 reduction for bike
     * @param vegmealsCO2     - new co2 reduction vor vegetarian meals
     * @param localproduceCO2 - new co2 reduction for local produce
     * @param publicCO2       - new co2 reduction for public transport
     * @param plasticrecyclingCO2 - new co2 reduction for plastic recycling
     * @param paperrecyclingCO2 - new co2 reduction for paper recycling
     * @param solarareaCO2 - new co2 reduction for solar panels
     * @param temperatureCO2 - new co2 reduction for home temperature
     */
    public FractalTreeResponse(
        double bikeCo2,
        double vegmealsCO2,
        double localproduceCO2,
        double publicCO2,
        double plasticrecyclingCO2,
        double paperrecyclingCO2,
        double solarareaCO2,
        double temperatureCO2) {
        this.bikeCo2 = bikeCo2;
        this.localproduceCO2 = localproduceCO2;
        this.vegmealsCO2 = vegmealsCO2;
        this.publicCO2 = publicCO2;
        this.plasticrecyclingCO2 = plasticrecyclingCO2;
        this.paperrecyclingCO2 = paperrecyclingCO2;
        this.solarareaCO2 = solarareaCO2;
        this.temperatureCO2 = temperatureCO2;
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

    public double getPlasticrecyclingCO2() { return plasticrecyclingCO2; }

    public double getPaperrecyclingCO2() { return paperrecyclingCO2; }

    public double getSolarareaCO2() { return solarareaCO2; }

    public double getTemperatureCO2() { return  temperatureCO2; }
}
