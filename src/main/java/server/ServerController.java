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

    //TODO: add input parameters in RequestBody (like user id) + retrieve user data from db.
    @RequestMapping(value = "/retrieve", method = RequestMethod.POST)
    public UserData retrieve_user_data(@RequestBody String uid) throws InterruptedException {

        UserData user = DatabaseHandler.getUserData(uid);
        user.vegmeals = DatabaseHandler.retrieveFeatureCounter(uid,"vegmeals");

        return user;
    }

    //TODO: increase score in db.
    @RequestMapping(value = "/vegmeal", method = RequestMethod.POST)
    public VegetarianResponse vegetarianMeal(@RequestBody VegetarianRequest vegetarianRequest) throws InterruptedException {
        int exp = DatabaseHandler.increaseExpBy(vegetarianRequest.getUid(), vegetarianRequest.getAmount() * 5);
        double co2 = DatabaseHandler.increaseCO2RedBy(vegetarianRequest.getUid(), vegetarianRequest.getAmount() * 3.0);
        int amount = DatabaseHandler.increaseFeatureCounter(vegetarianRequest.getUid(), "vegmeals", vegetarianRequest.getAmount());
        return new VegetarianResponse(exp, co2, amount);
    }

    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public ResponseEntity initUser(@RequestBody InitRequest initRequest) {
        DatabaseHandler.initUser(initRequest.getUid(), initRequest.getFname(), initRequest.getLname());
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
