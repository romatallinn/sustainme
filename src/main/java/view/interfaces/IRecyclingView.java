package view.interfaces;

import controller.RecyclingController;

public interface IRecyclingView {

    void initView(RecyclingController controller);

    void displayStatus(String msg);

    void updatePaperRecyclingCounter(double counter);

    void updatePlasticRecyclingCounter(double counter);
}
