package controller;

import model.BadgeModel;
import view.interfaces.IBadgesView;

/**
 * The controller that is responsible for badges view.
 * The view where user can see their achievements
 */
public class BadgesController {

    private IBadgesView view;
    private BadgeModel model;

    public BadgesController(IBadgesView view) {
        this.view = view;
        model = new BadgeModel();
    }

    public boolean getBadge(String badge) {
        return model.receiveBadge(badge);
    }
}
