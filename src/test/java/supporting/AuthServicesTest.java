package supporting;

import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.model.FirebaseResponse;
import org.junit.*;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class AuthServicesTest {


    @Test
    public void classTest() {
        Assert.assertNotNull(new AuthService());
    }

    @Test
    public void signInSuccessTest() {

        Boolean exThrown = false;

        try {

            FirebaseResponse response = AuthService.signIn("test@test.com", "123456Aa");
            Assert.assertTrue(response.getSuccess());

        } catch(FirebaseException e) {

            System.out.print(e.getLocalizedMessage());
            exThrown = true;

        } catch(JacksonUtilityException e) {

            System.out.print(e.getLocalizedMessage());
            exThrown = true;

        } catch(UnsupportedEncodingException e) {

            System.out.print(e.getLocalizedMessage());
            exThrown = true;

        }

        Assert.assertFalse(exThrown);

    }

    @Test
    public void signUpFailureExistingUserTest() {

        Boolean exThrown = false;

        try {

            FirebaseResponse response = AuthService.signUp("test@test.com", "123456Aa");
            Map<String, Object> errorObj = (Map<String, Object>) response.getBody().get("error");

            Assert.assertEquals(errorObj.get("message"), "EMAIL_EXISTS");

        } catch(FirebaseException e) {

            System.out.print(e.getLocalizedMessage());
            exThrown = true;

        } catch(JacksonUtilityException e) {

            System.out.print(e.getLocalizedMessage());
            exThrown = true;

        } catch(UnsupportedEncodingException e) {

            System.out.print(e.getLocalizedMessage());
            exThrown = true;

        }

        Assert.assertFalse(exThrown);

    }



}
