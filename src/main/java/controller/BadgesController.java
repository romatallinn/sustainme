package controller;

import view.interfaces.IBadgesView;

/**
 * The controller that is responsible for badges view.
 * The view where user can see their achievements
 */
public class BadgesController {

    private IBadgesView view;

    public BadgesController(IBadgesView view) {
        this.view = view;
    }
}
