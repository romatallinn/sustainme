package view.interfaces;

import controller.PaperRecyclingController;

public interface IPaperRecyclingView {

    void initView(PaperRecyclingController controller);

    void displayStatus(String msg);

    void updatePaperRecyclingCounter(float counter);
}
