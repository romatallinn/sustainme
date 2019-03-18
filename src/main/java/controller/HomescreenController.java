package controller;

import model.SingletonUser;
import view.interfaces.IHomeView;

/**
 * The controller that is responsible for homescreen view.
 * The main view where user can see his main stats and the buttons
 * to different features.
 */
public class HomescreenController {

    private IHomeView view;

    public HomescreenController(IHomeView view) {
        this.view = view;
    }


    /**
     * Updates the view with data from DB.
     */
    public void updateViewWithData() {

        String name = SingletonUser.getInstance().getFirstName() + " "
                + SingletonUser.getInstance().getLastName();
        int lvl = SingletonUser.getInstance().getLevel();
        double expProgress = SingletonUser.getInstance().getExp();
        double reduced = SingletonUser.getInstance().getCo2Reduction();

        view.updateNameLabel(name);
        view.updateLvlLabel(lvl);
        view.updateExpLabel(expProgress);
        view.updateReducedLabel(reduced);

    }

    /**
     * Signs user off the session.
     */
    public void logout() {
        SingletonUser.getInstance().logout();
    }




}
