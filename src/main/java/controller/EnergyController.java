package controller;

import view.interfaces.IEnergyView;

public class EnergyController {

    private IEnergyView view;

    /**
     * Constructor for the class.
     * @param view that is represented by the controller.
     */
    public EnergyController(IEnergyView view) {
        this.view = view;
    }

}
