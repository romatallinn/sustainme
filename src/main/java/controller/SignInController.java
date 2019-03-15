package controller;

import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.model.FirebaseResponse;
import supporting.AuthService;
import view.interfaces.ISignUpView;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class SignInController {

    // TODO: replace interfaces with the corresponding
    private ISignUpView view;

    public SignInController(ISignUpView view) {
        this.view = view;
    }

    /**
     * Sign In callback method; requested from the view.
     * @param email - user's email to be used for login.
     * @param pass - user's password to be used for login.
     */
    public void signInCallback(String email, String pass) {

        try {

            FirebaseResponse response = AuthService.signIn(email, pass);

            if(!response.getSuccess()) {

                Map<String, Object> errorObj = (Map<String, Object>)response.getBody().get("error");
                String errorMsg = getErrorMessage(errorObj.get("message").toString());
                view.displayStatus(errorMsg);

                return;

            }


            String token = (String)response.getBody().get("idToken");

            // TODO: Initialize the user profile with the authorized token
            // UserProfile.init(token);

            // TODO: Proceed To Homescreen

        } catch (FirebaseException e) {
            view.displayStatus("Firebase Exception:\n" + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            view.displayStatus("Unsupported Encoding Exception:\n" + e.getMessage());
        } catch (JacksonUtilityException e) {
            view.displayStatus("Jackson Utility Exception:\n" + e.getMessage());
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
                res = "Unknown error has happened!";
                break;
        }

        return res;

    }

}
