package controller;

import model.SingletonUser;
import view.interfaces.IHomeView;

public class HomescreenController {

    private IHomeView view;

    public HomescreenController(IHomeView view) {
        this.view = view;
    }


    public void updateViewWithData() {

        String name = SingletonUser.getInstance().getFirstName() + " " + SingletonUser.getInstance().getLastName();
        int lvl = SingletonUser.getInstance().getLevel();
        double expProgress = SingletonUser.getInstance().getExp();
        double reduced = SingletonUser.getInstance().getCo2Reduction();

        view.updateNameLabel(name);
        view.updateLvlLabel(lvl);
        view.updateExpLabel(expProgress);
        view.updateReducedLabel(reduced);

    }




}
