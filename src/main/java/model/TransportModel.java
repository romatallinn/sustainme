package model;

import model.objects.BikeRequest;
import model.objects.BikeResponse;
import model.objects.PublicTransportRequest;
import model.objects.PublicTransportResponse;
import org.springframework.web.client.RestTemplate;
import supporting.ServerApi;

public class TransportModel {

    private int bikeDistance = -1;
    private int publicDistance = -1;


    /**
     * Returns the total distance cycled in kilometers.
     * @return the total distance
     */
    public int getBikeDistance() {

        if (bikeDistance < 0) {
            this.init();
        }

        return bikeDistance;
    }

    /**
     * Returns the total distance traveled by public transport.
     * @return the total distance
     */
    public int getPublicDistance() {

        if (publicDistance < 0) {
            this.init();
        }

        return publicDistance;
    }



    /**
     * Initializes the transport features of the user profile.
     */
    public void init() {

        if (UserProfile.getInstance().authToken.isEmpty()) {
            bikeDistance = 0;
            publicDistance = 0;
            return;
        }

        addDistanceCycled(0);
        addBusDistanceTraveled(0);
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
        final String uri = ServerApi.HOST + ServerApi.PUBLIC_TRANSPORT;

        PublicTransportRequest publicTransportRequest = new PublicTransportRequest(UserProfile.getInstance().getUid(), distance, false);

        RestTemplate restTemplate = new RestTemplate();
        PublicTransportResponse result =
                restTemplate.postForObject(uri, publicTransportRequest, PublicTransportResponse.class);

        UserProfile.getInstance().setLocalExp(result.getExperience());
        UserProfile.getInstance().setLocalCo2Stats(result.getCo2Reduced());

        publicDistance = result.getDistance();
    }

    public void addBusDistanceTraveled(int distance) {
        final String uri = ServerApi.HOST + ServerApi.PUBLIC_TRANSPORT;

        PublicTransportRequest publicTransportRequest = new PublicTransportRequest(UserProfile.getInstance().getUid(), distance, true);

        RestTemplate restTemplate = new RestTemplate();
        PublicTransportResponse result =
                restTemplate.postForObject(uri, publicTransportRequest, PublicTransportResponse.class);

        UserProfile.getInstance().setLocalExp(result.getExperience());
        UserProfile.getInstance().setLocalCo2Stats(result.getCo2Reduced());

        publicDistance = result.getDistance();
    }

}
