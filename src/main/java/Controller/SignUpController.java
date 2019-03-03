package controller;

import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.model.FirebaseResponse;
import supporting.AuthService;
import view.interfaces.ISignUpView;

import java.io.UnsupportedEncodingException;

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
            view.displayStatus("\n\n" + response.getRawBody() + "\n\n");
            view.clearSignUpFields();
        } catch (FirebaseException e) {
            view.displayStatus("Firebase Exception:\n" + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            view.displayStatus("Unsupported Encoding Exception:\n" + e.getMessage());
        } catch (JacksonUtilityException e) {
            view.displayStatus("Jackson Utility Exception:\n" + e.getMessage());
        }

    }

    /**
     * Sign In callback method; requested from the view.
     * @param email - user's email to be used for login.
     * @param pass - user's password to be used for login.
     */
    public void signInCallback(String email, String pass) {

        try {
            FirebaseResponse response = AuthService.signIn(email, pass);
            view.displayStatus("\n\n" + response.getRawBody() + "\n\n");
        } catch (FirebaseException e) {
            view.displayStatus("Firebase Exception:\n" + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            view.displayStatus("Unsupported Encoding Exception:\n" + e.getMessage());
        } catch (JacksonUtilityException e) {
            view.displayStatus("Jackson Utility Exception:\n" + e.getMessage());
        }

    }

}
