package server.supporting;

import com.google.firebase.FirebaseApp;
import org.junit.Assert;
import org.junit.Test;

public class FirebaseConnectionTest {

    @Test
    public void testInit() {

        for (FirebaseApp app : FirebaseApp.getApps()) {
            app.delete();
        }

        FirebaseConnection.initApp();

        Assert.assertNotEquals(0, FirebaseApp.getApps().size());

    }

}
