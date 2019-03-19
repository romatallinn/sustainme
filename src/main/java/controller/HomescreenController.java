package controller;

import model.objects.UserProfile;
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

        String name = UserProfile.getInstance().getFirstName() + " "
                + UserProfile.getInstance().getLastName();
        int lvl = UserProfile.getInstance().getLevel();
        double expProgress = UserProfile.getInstance().getExpProgress();
        double reduced = UserProfile.getInstance().getCo2Reduction();

        view.updateNameLabel(name);
        view.updateLvlLabel(lvl);
        view.updateExpLabel(expProgress);
        view.updateReducedLabel(reduced);

    }

    /**
     * Signs user off the session.
     */
    public void logout() {
        UserProfile.getInstance().logout();
    }




}
