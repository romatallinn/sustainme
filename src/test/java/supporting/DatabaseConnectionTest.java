package supporting;

import net.thegreshams.firebase4j.error.FirebaseException;
import org.junit.*;


public class DatabaseConnectionTest {

    @Test
    public void constructorTest() {

        Boolean exThrown = false;

        try {
            new DatabaseConnection();
        } catch (FirebaseException e) {

            System.out.print(e.getLocalizedMessage());
            exThrown = true;

        }

        Assert.assertFalse(exThrown);

    }

}
