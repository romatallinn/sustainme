package controller;

import model.objects.UserProfile;
import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.model.FirebaseResponse;
import supporting.AuthService;
import view.interfaces.ISignInView;

import java.io.IOException;
import java.util.Map;

public class SignInController {

    private ISignInView view;

    public SignInController(ISignInView view) {
        this.view = view;
    }

    /**
     * Sign In callback method; requested from the view.
     * @param email - user's email to be used for goToSignin.
     * @param pass - user's password to be used for goToSignin.
     */
    public void signInCallback(String email, String pass) {

        try {

            FirebaseResponse response = AuthService.signIn(email, pass);

            if (!response.getSuccess()) {

                Map<String, Object> errorObj = (Map<String, Object>)response.getBody().get("error");
                String errorMsg = getErrorMessage(errorObj.get("message").toString());
                view.displayStatus(errorMsg);

                return;

            }


            String token = (String)response.getBody().get("idToken");
            String emailStr = (String)response.getBody().get("email");
            String uid = (String)response.getBody().get("localId");

            UserProfile.getInstance().init(emailStr, uid, token);
            view.goToHome();

        } catch (FirebaseException | JacksonUtilityException | IOException e) {
            view.displayStatus("Exception:\n" + e.getMessage());
        }

    }

    /**
     * Method for interpreting the error code response from DB into a user-friendly message.
      * @param error - the error code
     * @return a user-friendly message that describes an occurred issue.
     */
    private String getErrorMessage(String error) {

        String res = "";

        switch (error) {
            case "EMAIL_NOT_FOUND":
                res = "The user with such email was not found!";
                break;

            case "INVALID_PASSWORD":
                res = "The password for the given email is invalid!";
                break;

            default:
                res = error;
                break;
        }

        return res;

    }

}
