package controller;

import model.objects.UserData;
import view.interfaces.IFriendsComparisonView;

public class FriendsComparisonController {

    private IFriendsComparisonView view;

    public FriendsComparisonController(IFriendsComparisonView view) {
        this.view = view;
    }

}
