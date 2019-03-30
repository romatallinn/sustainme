package model;

import model.objects.BikeRequest;
import model.objects.BikeResponse;
import org.springframework.web.client.RestTemplate;
import supporting.ServerApi;

public class TransportModel {

    private int bikeDistance = -1;

    public int getBikeDistance() {

        if (bikeDistance < 0) {
            this.init();
        }

        return bikeDistance;
    }

    public void init() {

        if (UserProfile.getInstance().authToken.isEmpty()) {
            bikeDistance = 0;
            return;
        }

        // TODO: fix workaround
        addDistanceCycled(0);
    }


    /**
     * Invokes request to server, notifying it about the action of cycled distance.
     * @param distance distance cycled
     */
    public void addDistanceCycled(int distance) {

        final String uri = ServerApi.HOST + ServerApi.BICYCLE;

        BikeRequest bikeRequest =
                new BikeRequest(UserProfile.getInstance().getUid(), distance);

        RestTemplate restTemplate = new RestTemplate();
        BikeResponse result =
                restTemplate.postForObject(uri, bikeRequest, BikeResponse.class);

        UserProfile.getInstance().setLocalExp(result.getExperience());
        UserProfile.getInstance().setLocalCo2Stats(result.getCo2Reduced());

        bikeDistance = result.getDistance();
    }


    public void addTrainDistanceTraveled(int distance) {


    }

    public void addBusDistanceTraveled(int distance) {


    }

}
