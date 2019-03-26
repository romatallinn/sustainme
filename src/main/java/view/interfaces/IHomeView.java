package view.interfaces;

import controller.HomescreenController;

/**
 * Interface of the home view.
 * It is used in order to make a more abstracted connection,
 * between the controller and possible views (mobile view, javafx or terminal).
 */
public interface IHomeView {

    void initView(HomescreenController controller);

    void updateNameLabel(String name);

    void updateLvlLabel(int lvl);

    void updateExpLabel(double expProgress);

    void updateReducedLabel(double redCO2);

}