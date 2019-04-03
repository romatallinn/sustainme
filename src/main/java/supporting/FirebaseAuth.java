package supporting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


import java.net.HttpURLConnection;
import java.net.URL;

public class FirebaseAuth {

    private static final String BASE_URL = "https://www.googleapis.com/identitytoolkit/v3/relyingparty/";
    private static final String OPERATION_SIGNIN = "verifyPassword";
    private static final String OPERATION_SIGNUP = "signupNewUser";

    private static FirebaseAuth instance = null;


    /**
     * Get instance of the FirebaseAuth Singleton class.
     * @return FirebaseAuth singleton class.
     */
    public static FirebaseAuth getInstance() {
        if (instance == null) {
            instance = new FirebaseAuth();
        }
        return instance;
    }

    /**
     * Parse the JsonObject to find whether or not there is an error response.
     * @param obj - the JsonObject of the server's response.
     * @return the error message if any (null otherwise).
     */
    public static String parseError(JsonObject obj) {

        if (obj.get("error") == null) {
            return null;
        }

        String msg = obj.get("error").getAsJsonObject().get("message").getAsString();
        String[] split = msg.split(" : ");

        if (split.length == 2) {
            return split[1];
        }

        return msg;

    }


    /**
     * Authorize user with given credentials.
     * @param email - email of the user.
     * @param password - password of the user.
     * @return JsonObject of the server's response.
     */
    public JsonObject auth(String email, String password) {

        String jsonRequest = "{\"email\":\"" + email
                            + "\",\"password\":\"" + password
                            + "\",\"returnSecureToken\":true}";

        return httpRequest(OPERATION_SIGNIN, jsonRequest);

    }

    /**
     * Register user with given credentials.
     * @param email - email of the user.
     * @param password - password of the user.
     * @return JsonObject of the server's response.
     */
    public JsonObject register(String email, String password) {

        String jsonRequest = "{\"email\":\"" + email
                            + "\",\"password\":\"" + password
                            + "\",\"returnSecureToken\":true}";

        return httpRequest(OPERATION_SIGNUP, jsonRequest);

    }



    private JsonObject httpRequest(String operation, String jsonRequest) {

        HttpURLConnection urlRequest = null;
        JsonObject obj = null;
        JsonParser jp = new JsonParser();

        try {

            URL url = new URL(BASE_URL + operation + "?key=" + ServerApi.appKey);
            urlRequest = (HttpURLConnection) url.openConnection();
            urlRequest.setDoOutput(true);
            urlRequest.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            OutputStream os = urlRequest.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(jsonRequest);
            osw.flush();
            osw.close();

            urlRequest.connect();

            JsonElement root = jp.parse(
                    new InputStreamReader((InputStream) urlRequest.getContent()));

            obj = root.getAsJsonObject();

        } catch (IOException e) {

            JsonElement root = jp.parse(
                    new InputStreamReader((InputStream) urlRequest.getErrorStream()));

            obj = root.getAsJsonObject();

        } finally {
            urlRequest.disconnect();
        }

        return obj;

    }

}