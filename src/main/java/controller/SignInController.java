package controller;

import com.google.gson.JsonObject;

import model.UserProfile;

import supporting.FirebaseAuth;

import view.interfaces.ISignInView;

import java.io.IOException;

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

            JsonObject jsonObj = FirebaseAuth.getInstance().auth(email, pass);
            String err = FirebaseAuth.parseError(jsonObj);

            if (err != null) {

                String msg = getErrorMessage(err);
                view.displayStatus(msg);
                return;

            }

            String token = jsonObj.get("idToken").getAsString();
            if (token.isEmpty()) {
                view.displayStatus("Could not sign in for unknown reason!");
                return;
            }

            String uid = jsonObj.get("localId").getAsString();

            UserProfile.getInstance().init(email, uid, token);
            view.goToHome();

        } catch (IOException e) {
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
            case "INVALID_EMAIL":
                res = "Invalid email address";
                break;

            case "INVALID_PASSWORD":
                res = "Invalid password";
                break;


            default:
                res = error;
                break;

        }

        return res;

    }

}
