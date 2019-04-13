package controller;

import model.EnergyModel;
import view.interfaces.IEnergyView;

public class EnergyController {

    private IEnergyView view;
    private EnergyModel model;

    /**
     * Constructor for the class.
     * @param view that is represented by the controller.
     */
    public EnergyController(IEnergyView view) {
        this.view = view;
        this.model = new EnergyModel();
    }

    /**
     * Retrieves current solar panel area and temperature, and displays it.
     */
    public void updateViewWithData() {

        int solarArea = model.getSolarArea();
        view.updateSolarArea(solarArea);

        double temperature = model.getHomeTemperature();
        view.updateTemperature(temperature);

    }

    /**
     * Client side of updating the solar panel area.
     * @param areaString - value by which the solar panel area should be increased.
     */
    public void addSolarArea(String areaString) {
        int area = 0;
        try {
            area = Integer.parseInt(areaString);
            if (area < 0 || area > 100) {
                view.displayStatusSolar("Please enter a  number in the range 0-100 square meters.");
                return;
            }
            model.addSolarArea(area);
            view.displayStatusSolar("The stat of the solar panels is updated!");
            updateViewWithData();
        } catch (NumberFormatException e) {
            view.displayStatusSolar("Please enter an integer number.");
        }
    }

    /**
     * Client side of updating the temperature.
     * @param temperatureString - the value to which the current temperature should be changed.
     */
    public void changeTemperature(String temperatureString) {
        float temperature = 0;
        try {
            temperature = Float.parseFloat(temperatureString);
            if (temperature < 0 || temperature > 40) {
                view.displayStatusTemperature("Please enter the temperature in the range 0-40 degrees Celsius.");
                return;
            }
            model.lowerTemperature(temperature);
            view.displayStatusTemperature("The stat of the temperature is updated!");
            updateViewWithData();
        } catch (NumberFormatException e) {
            view.displayStatusTemperature("Please enter a number.");
        }

    }
}
