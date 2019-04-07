package view.interfaces;

import controller.RecyclingController;

public interface IRecyclingView {

    void initView(RecyclingController controller);

    void displayStatus(String msg);

    void updatePaperRecyclingCounter(float counter);

    void updatePlasticRecyclingCounter(float counter);
}
