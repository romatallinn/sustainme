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
     * Retrieves stats and sends them to the view for update.
     */

    public void updateViewWithData() {
        int solarPanelArea = model.getSolarPanelArea();

        // TODO
        // view.updateSolarPanelArea(solarPanelArea);
    }

    /**
     * The method acts upon the fact of the increased solar panel area coverage.
     * @param areaStr of the m2 area coverage increase.
     */

    public void addSolarPanelArea(String areaStr) {

        int area = 0;
        try {
            area = Integer.parseInt(areaStr);
            if (area < 0 || area > 500) {
                view.displayStatus("Please enter a number in the range 0-500m2.");
                return;
            }
            model.addAreaSolarPanels(area);
            view.displayStatus("The stat of the area covered with solar panels is updated!");
            updateViewWithData();
        } catch (NumberFormatException e) {
            view.displayStatus("Please enter an integer number.");
        }

    }

}
