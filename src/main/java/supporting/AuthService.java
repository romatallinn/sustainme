package supporting;

import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.model.FirebaseResponse;
import net.thegreshams.firebase4j.service.Firebase;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

public final class AuthService {

    private static final String signUp = "signupNewUser";
    private static final String signIn = "verifyPassword";

    /**
     * The Sign In authentication request.
     * @param email - the user's email to be authenticated.
     * @param pass - the user's password to be authenticated.
     * @return FirebaseResponse from the server.
     * @throws FirebaseException - the exception in service connection.
     * @throws JacksonUtilityException - jackson utility exception.
     * @throws UnsupportedEncodingException - unsupported encoding of the response.
     */
    public static FirebaseResponse signIn(String email, String pass)
            throws FirebaseException, JacksonUtilityException, UnsupportedEncodingException {

        Firebase connection = new Firebase(AppConfig.authUrl, false);
        connection.addQuery("key", AppConfig.appKey);


        return connection.post(signIn, credentialsPostMap(email, pass));
    }

    /**
     * The Sign Up authentication request.
     * @param email - the user's email to be registered.
     * @param pass - the user's password to be registered.
     * @return FirebaseResponse from the server.
     * @throws FirebaseException - the exception in service connection.
     * @throws JacksonUtilityException - jackson utility exception.
     * @throws UnsupportedEncodingException - unsupported encoding of the response.
     */
    public static FirebaseResponse signUp(String email, String pass)
            throws FirebaseException, JacksonUtilityException, UnsupportedEncodingException {

        Firebase connection = new Firebase(AppConfig.authUrl, false);
        connection.addQuery("key", AppConfig.appKey);

        return connection.post(signUp, credentialsPostMap(email, pass));

    }

    /**
     * Helper function to create the map object of the user's credentials.
     * @param email - the user's email to be used.
     * @param pass - the user's password to be used.
     * @return the map object of the user's credentials.
     */
    private static Map<String, Object> credentialsPostMap(String email, String pass) {

        Map<String, Object> dataMap = new LinkedHashMap<>();
        dataMap.put("email", email);
        dataMap.put("password", pass);
        dataMap.put("returnSecureToken", true);

        return dataMap;

    }

}
