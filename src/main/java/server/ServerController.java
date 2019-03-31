package server;

import api.ApiRequest;
import model.objects.BikeRequest;
import model.objects.BikeResponse;
import model.objects.InitRequest;
import model.objects.LocalProduceRequest;
import model.objects.LocalProduceResponse;
import model.objects.PublicTransportRequest;
import model.objects.PublicTransportResponse;
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
        int upAmount = vegetarianRequest.getAmount();
        if (upAmount <= 0 || upAmount > 3) {
            upAmount = 0;
        }
        int exp = DatabaseHandler.increaseExpBy(vegetarianRequest.getUid(),
                upAmount * 20);
        double co2 = DatabaseHandler.increaseCO2RedBy(vegetarianRequest.getUid(),
                upAmount * 3.0);
        int amount = DatabaseHandler.increaseFeatureCounter(vegetarianRequest.getUid(),
                "vegmeals", upAmount);
        return new VegetarianResponse(exp, co2, amount);

    }

    /**
     * Sends a request to the database handler for updating the vegetarian meal stats.
     * @param localProduceRequest request sent by client
     * @return LocalProduceResponse for user with updated stats
     * @throws InterruptedException exception could be thrown by database handler
     */
    @RequestMapping(value = "/localproduce", method = RequestMethod.POST)
    public LocalProduceResponse localProduce(@RequestBody LocalProduceRequest localProduceRequest)
            throws InterruptedException {
        int exp = DatabaseHandler.increaseExpBy(localProduceRequest.getUid(),
                (int) Math.round(localProduceRequest.getWeight()));
        double co2 = DatabaseHandler.increaseCO2RedBy(localProduceRequest.getUid(),
                localProduceRequest.getWeight() * 0.14);
        double amount = DatabaseHandler.increaseFeatureCounter(localProduceRequest.getUid(),
                "localproduce", localProduceRequest.getWeight());
        return new LocalProduceResponse(exp, co2, amount);

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

    /**
     * Sends a request to the database handler for updating the bike stats.
     * @param bikeRequest request received by client
     * @return bikeResponse for user with updated stats
     * @throws Exception exception could be thrown by database handler
     */
    @RequestMapping(value = "/bike", method = RequestMethod.POST)
    public BikeResponse useBike(@RequestBody BikeRequest bikeRequest) throws Exception {
        double result = ApiRequest.requestBike(Double
                .toString(bikeRequest.getDistance() * 0.621371192)); //Should be
        // result from api request
        int exp =  DatabaseHandler.increaseExpBy(bikeRequest.getUid(),
                bikeRequest.getDistance());
        double co2 = DatabaseHandler.increaseCO2RedBy(bikeRequest.getUid(),
                result * 1000);
        int distance = DatabaseHandler.increaseFeatureCounter(bikeRequest.getUid(), "bike",
                bikeRequest.getDistance());

        return new BikeResponse(exp,co2,distance);

    }

    /**
     * Sends a request to the database handler for updating the public transport stats.
     * @param publicTransportRequest request sent by client
     * @return publicTransportResponse for user with updated stats
     * @throws Exception exception could be thrown by database handler
     */
    @RequestMapping(value = "/publictransport", method = RequestMethod.POST)
    public PublicTransportResponse usePublicTransport(
            @RequestBody PublicTransportRequest publicTransportRequest) throws Exception {
        double result = ApiRequest.requestPublicTrans(Double
                .toString(publicTransportRequest.getDistance() * 0.621371192),
                publicTransportRequest.getType()); //Should be
        // result from api request
        int exp =  DatabaseHandler.increaseExpBy(publicTransportRequest.getUid(),
                publicTransportRequest.getDistance() / 2);
        double co2 = DatabaseHandler.increaseCO2RedBy(publicTransportRequest.getUid(),
                result * 1000);
        int distance = DatabaseHandler.increaseFeatureCounter(
                publicTransportRequest.getUid(), "public",
                publicTransportRequest.getDistance());

        return new PublicTransportResponse(exp,co2,distance);

    }

}
