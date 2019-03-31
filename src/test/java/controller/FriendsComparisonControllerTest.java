package controller;

import org.junit.Assert;
import org.junit.Test;
import view.implementation.javafx.JavaFxFriendsComparisonView;
import view.interfaces.IFriendsComparisonView;

public class FriendsComparisonControllerTest {

    private FriendsComparisonController controller;

    @Test
    public void testSetup() {
        IFriendsComparisonView view = new JavaFxFriendsComparisonView();
        controller = new FriendsComparisonController(view);
        Assert.assertNotNull(controller);
    }


}
