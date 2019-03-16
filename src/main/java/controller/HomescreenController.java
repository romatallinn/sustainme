package controller;

import view.interfaces.IHomeView;

public class HomescreenController {

    private IHomeView view;

    public HomescreenController(IHomeView view) {
        this.view = view;
        //updateViewWithData();
    }


    /*public void updateViewWithData() {

        String name = UserProfile.getFirstName() + UserProfile.getLastName();
        String lvl = UserProfile.getLevel();
        String exp = UserProfile.getExperience();
        String reduced = UserProfile.getCo2Reduction();

        view.updateNameLabel(name);
        view.updateLvlLabel(lvl);
        view.updateExpLabel(exp);
        view.updateReducedLabel(reduced);

    }*/




}
