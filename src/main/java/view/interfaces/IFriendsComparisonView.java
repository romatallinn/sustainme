package view.interfaces;

import controller.FriendsComparisonController;
import model.objects.UserData;

public interface IFriendsComparisonView {

    void initView(FriendsComparisonController controller);

    void updateFriendsData(UserData friend);

}
