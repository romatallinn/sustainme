package supporting;

import com.google.gson.JsonObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;

public class FirebaseAuthTest {

    private final String uid = "5An25iOCs5bisQ2ORzaaoUD9nNo2";

    private final String testEmail = "test@test.com";
    private final String regEmail = "reg@test.com";

    private final String testPass = "123456Aa";

    @Test
    public void testAuthSuccess() {

        JsonObject response = FirebaseAuth.getInstance().auth(testEmail, testPass);

        Assert.assertNull(FirebaseAuth.parseError(response));

        String uidActual = response.get("localId").getAsString();
        Assert.assertEquals(uid, uidActual);

    }

    @Test
    public void testAuthFailure() {

        JsonObject response = FirebaseAuth.getInstance().auth(testEmail, "1234");
        Assert.assertNotNull(FirebaseAuth.parseError(response));

    }

    @Test
    public void testRegSmallPassFailure() {

        JsonObject response = FirebaseAuth.getInstance().register("awdw@awd.ru", "123");
        Assert.assertNotNull(FirebaseAuth.parseError(response));

    }

    @Test
    public void testRegWrongPassFailure() {

        JsonObject response = FirebaseAuth.getInstance().register(testEmail, testPass);
        Assert.assertNotNull(FirebaseAuth.parseError(response));

    }


    @Test
    public void testRegDeleteSuccess() {

        JsonObject response = FirebaseAuth.getInstance().register(regEmail, testPass);
        String uid = response.get("idToken").getAsString();

        Assert.assertNull(FirebaseAuth.parseError(response));

        response = FirebaseAuth.getInstance().delete(uid);
        Assert.assertNull(FirebaseAuth.parseError(response));

        response = FirebaseAuth.getInstance().auth(regEmail, testPass);
        Assert.assertEquals("EMAIL_NOT_FOUND", response.get("error").getAsJsonObject().get("message").getAsString());

    }
}
