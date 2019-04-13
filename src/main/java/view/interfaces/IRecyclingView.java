package view.interfaces;

import controller.RecyclingController;

public interface IRecyclingView {

    void initView(RecyclingController controller);

    void displayStatusPaper(String msg);

    void displayStatusPlastic(String msg);

    void updatePaperRecyclingCounter(double counter);

    void updatePlasticRecyclingCounter(double counter);
}
