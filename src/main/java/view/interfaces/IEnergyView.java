package view.interfaces;

import controller.EnergyController;

public interface IEnergyView {

    void initView(EnergyController controller);

    void displayStatusSolar(String msg);

    void displayStatusTemperature(String msg);

    void updateTemperature (double temp);

    void updateSolarArea (int area);
}
