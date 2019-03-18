package controller;

import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.model.FirebaseResponse;
import supporting.AuthService;
import view.interfaces.ISignUpView;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class SignUpController {

    private ISignUpView view;

    public SignUpController(ISignUpView view) {
        this.view = view;
    }

    /**
     * Sign Up callback method; requested from the view.
     * @param email - user's email to be used for registration.
     * @param pass - user's password to be used for registration.
     */
    public void signUpCallback(String email, String pass) {

        try {
            FirebaseResponse response = AuthService.signUp(email, pass);

            if (!response.getSuccess()) {
                Map<String, Object> errorObj = (Map<String, Object>)response.getBody().get("error");
                String errorMsg = getErrorMessage(errorObj.get("message").toString());
                view.displayStatus(errorMsg);
            } else {
                view.displayStatus("You were registered!");
                view.clearSignUpFields();

                // TODO: Automatic Authorization?
            }

        } catch (FirebaseException | UnsupportedEncodingException | JacksonUtilityException e) {
            view.displayStatus("Exception:\n" + e.getMessage());
        }

    }

    /**
     * Method for interpreting the error code response from DB into a user-friendly message.
     * @param error - the error code
     * @return a user-friendly message that describes an occurred issue.
     */
    public void signInCallback(String email, String pass) {

        try {
            FirebaseResponse response = AuthService.signIn(email, pass);
            view.displayStatus("\n\n" + response.getRawBody() + "\n\n");
        } catch (FirebaseException | UnsupportedEncodingException | JacksonUtilityException e) {
            view.displayStatus("Exception:\n" + e.getMessage());
        }

    }

    private String getErrorMessage(String error) {

        String res = "";

        switch (error) {
            case "INVALID_EMAIL":
                res = "The email follows the wrong format!";
                break;

            case "EMAIL_EXISTS":
                res = "The email is already signed up!";
                break;

            default:
                res = error;
                break;
        }

        return res;

    }

}
