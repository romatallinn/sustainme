package view.interfaces;

import controller.EnergyController;

public interface IEnergyView {

    void initView(EnergyController controller);

    void displayStatus(String msg);

}