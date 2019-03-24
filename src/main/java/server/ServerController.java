package server;

import model.objects.InitRequest;
import model.objects.UserData;
import model.objects.VegetarianRequest;
import model.objects.VegetarianResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.supporting.DatabaseHandler;

@RestController
public class ServerController {

    /**
     * Retrieving the user's data by id.
     * @param uid - user id.
     * @return UserData object.
     * @throws InterruptedException - exception.
     */
    @RequestMapping(value = "/retrieve", method = RequestMethod.POST)
    public UserData retrieve_user_data(@RequestBody String uid) throws InterruptedException {

        UserData user = DatabaseHandler.getUserData(uid);
        user.vegmeals = DatabaseHandler.retrieveFeatureCounter(uid,"vegmeals");

        return user;
    }

    /**
     * Sends a request to the database handler for updating the vegetarian meal stats.
     * @param vegetarianRequest request received by client
     * @return vegetarianResponse for user with updated stats
     * @throws InterruptedException exception could be thrown by database handler
     */
    @RequestMapping(value = "/vegmeal", method = RequestMethod.POST)
    public VegetarianResponse vegetarianMeal(@RequestBody VegetarianRequest vegetarianRequest)
            throws InterruptedException {

        int exp = DatabaseHandler.increaseExpBy(vegetarianRequest.getUid(),
                vegetarianRequest.getAmount() * 5);
        double co2 = DatabaseHandler.increaseCO2RedBy(vegetarianRequest.getUid(),
                vegetarianRequest.getAmount() * 3.0);
        int amount = DatabaseHandler.increaseFeatureCounter(vegetarianRequest.getUid(),
                "vegmeals", vegetarianRequest.getAmount());
        return new VegetarianResponse(exp, co2, amount);

    }

    /**
     * Sends a request to the server to initialize a new user.
     * @param initRequest contains user credentials
     * @return a response to confirm that the user has been initialized
     */
    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public ResponseEntity initUser(@RequestBody InitRequest initRequest) {
        DatabaseHandler.initUser(initRequest.getUid(), initRequest.getFname(),
                initRequest.getLname());
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
