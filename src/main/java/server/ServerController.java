package server;

import model.objects.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {

    //TODO: add input parameters in RequestBody (like user id) + retrieve user data from db.
    @RequestMapping(value = "/retrieve", method = RequestMethod.POST)
    public UserProfile retrieve_user_data(@RequestBody String user_id){
        UserProfile user = new UserProfile();
        return user;
    }

    //TODO: increase score in db.
    @RequestMapping(value = "/increase_score", method= RequestMethod.POST)
    public ResponseEntity increase_score(@RequestBody int score){

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/update_co2", method = RequestMethod.POST)
    public ResponseEntity update_co2(@RequestBody int co2){
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
