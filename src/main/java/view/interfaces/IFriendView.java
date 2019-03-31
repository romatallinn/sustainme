package view.interfaces;

import controller.FriendsController;
import model.objects.UserData;

import java.util.List;

public interface IFriendView {

    void initView(FriendsController controller);

    void updateFriendsList(List<UserData> friends);

    void displayStatus(String msg);

}
