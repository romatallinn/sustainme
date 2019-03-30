package controller;

import com.google.gson.JsonObject;

import model.UserProfile;
import model.objects.InitRequest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import supporting.FirebaseAuth;

import supporting.ServerApi;
import view.interfaces.ISignUpView;

import java.io.IOException;

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
    public void signUpCallback(String email, String pass, String repass, String fname, String lname) {

        if (!pass.equals(repass)) {
            view.displayStatus("Passwords do not match!");
            return;
        }

        try {

            JsonObject jsonObj = FirebaseAuth.getInstance().register(email, pass);
            String err = FirebaseAuth.parseError(jsonObj);

            if (err != null) {

                String msg = getErrorMessage(err);
                view.displayStatus(msg);
                return;

            }

            String uid = jsonObj.get("localId").getAsString();

            final String uri = ServerApi.HOST + ServerApi.USER_DB_INIT;

            InitRequest initRequest = new InitRequest(uid, fname, lname);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity response = restTemplate.postForObject(uri, initRequest,
                    ResponseEntity.class);
            System.out.println(response);


            jsonObj = FirebaseAuth.getInstance().auth(email, pass);
            err = FirebaseAuth.parseError(jsonObj);

            if (err != null) {

                view.displayStatus(err);
                return;

            }

            String token = jsonObj.get("idToken").getAsString();
            if (token.isEmpty()) {
                view.displayStatus("Could not sign in for unknown reason!");
                return;
            }

            UserProfile.getInstance().init(email, uid, token);

            view.goToHome();


        } catch (IOException e) {
            view.displayStatus("Exception:\n" + e.getMessage());
        }

    }

    /**
     * Method for interpreting the error code response from DB into a user-friendly message.
     * @param error - the error code.
     * @return a user-friendly message that describes an occurred issue.
     */
    private String getErrorMessage(String error) {

        String res = "";

        switch (error) {
            case "INVALID_EMAIL":
                res = "Invalid email address";
                break;

            case "EMAIL_EXISTS":
                res = "Email already in use";
                break;

            default:
                res = error;
                break;
        }

        return res;

    }

}
