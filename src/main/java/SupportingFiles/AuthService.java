package SupportingFiles;

import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.model.FirebaseResponse;
import net.thegreshams.firebase4j.service.Firebase;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import java.net.HttpURLConnection;

public class AuthService {

    private final static String signUp = "signupNewUser";
    private final static String signIn = "verifyPassword";

    public static FirebaseResponse SignIn(String email, String pass) throws FirebaseException, JacksonUtilityException, UnsupportedEncodingException
    {

        Firebase connection = new Firebase(AppConfig.authUrl);
        connection.addQuery("key", AppConfig.appKey);


        return connection.post(signIn, CredentialsPostMap(email, pass));
    }


    public static FirebaseResponse SignUp(String email, String pass) throws FirebaseException, JacksonUtilityException, UnsupportedEncodingException
    {
        Firebase connection = new Firebase(AppConfig.authUrl);
        connection.addQuery("key", AppConfig.appKey);

        return connection.post(signUp, CredentialsPostMap(email, pass));

    }

    private static Map<String, Object> CredentialsPostMap(String email, String pass) {

        Map<String, Object> dataMap = new LinkedHashMap<>();
        dataMap.put("email", email);
        dataMap.put("password", pass);
        dataMap.put("returnSecureToken", true);

        return dataMap;

    }

}
