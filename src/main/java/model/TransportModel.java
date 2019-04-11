package model;

import model.objects.BikeRequest;
import model.objects.BikeResponse;
import model.objects.PublicTransportRequest;
import model.objects.PublicTransportResponse;
import org.springframework.web.client.RestTemplate;
import supporting.ServerApi;
import view.element.WindowsNotifications;

import java.awt.AWTException;
import java.net.MalformedURLException;

public class TransportModel {


    public Boolean badgeBike = false;
    public Boolean badgeNoCar = false;
    public Boolean badgePublic = false;
    private int bikeDistance = -1;
    private int publicDistance = -1;


    /**
     * Returns the total distance cycled in kilometers.
     *
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
     *
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
        badgeBike = new BadgeModel().receiveBadge("distanceByBikeBadge");
        badgeNoCar = new BadgeModel().receiveBadge("kmNoCarUsedBadge");
        badgePublic = new BadgeModel().receiveBadge("distancePublicBadge");
    }


    /**
     * Invokes request to server, notifying it about the action of cycled distance.
     *
     * @param distance distance cycled
     */
    public void addDistanceCycled(int distance) {

        if (UserProfile.getInstance().authToken.isEmpty()) {
            bikeDistance = 0;
            return;
        }

        final String uri = ServerApi.HOST + ServerApi.BICYCLE;

        BikeRequest bikeRequest =
            new BikeRequest(UserProfile.getInstance().getUid(), distance);

        RestTemplate restTemplate = new RestTemplate();
        BikeResponse result =
            restTemplate.postForObject(uri, bikeRequest, BikeResponse.class);

        UserProfile.getInstance().setLocalExp(result.getExperience());
        UserProfile.getInstance().setLocalCo2Stats(result.getCo2Reduced());

        bikeDistance = result.getDistance();

        checkBadgeBike();
        checkBadgeNoCar();
    }


    /**
     * Invokes request to server, notifying it about the action of distance by train.
     *
     * @param distance distance by train
     */
    public void addTrainDistanceTraveled(int distance) {

        if (UserProfile.getInstance().authToken.isEmpty()) {
            publicDistance = 0;
            return;
        }

        final String uri = ServerApi.HOST + ServerApi.PUBLIC_TRANSPORT;

        PublicTransportRequest publicTransportRequest =
            new PublicTransportRequest(UserProfile.getInstance().getUid(),
                distance, false);

        RestTemplate restTemplate = new RestTemplate();
        PublicTransportResponse result =
            restTemplate.postForObject(uri, publicTransportRequest,
                PublicTransportResponse.class);

        UserProfile.getInstance().setLocalExp(result.getExperience());
        UserProfile.getInstance().setLocalCo2Stats(result.getCo2Reduced());

        publicDistance = result.getDistance();

        checkBadgeNoCar();
    }

    /**
     * Invokes request to server, notifying it about the action of distance by bus.
     *
     * @param distance distance by bus
     */
    public void addBusDistanceTraveled(int distance) {

        if (UserProfile.getInstance().authToken.isEmpty()) {
            publicDistance = 0;
            return;
        }

        final String uri = ServerApi.HOST + ServerApi.PUBLIC_TRANSPORT;

        PublicTransportRequest publicTransportRequest =
            new PublicTransportRequest(UserProfile.getInstance().getUid(),
                distance, true);

        RestTemplate restTemplate = new RestTemplate();
        PublicTransportResponse result =
            restTemplate.postForObject(uri, publicTransportRequest,
                PublicTransportResponse.class);

        UserProfile.getInstance().setLocalExp(result.getExperience());
        UserProfile.getInstance().setLocalCo2Stats(result.getCo2Reduced());

        publicDistance = result.getDistance();

        checkBadgeNoCar();
        checkBadgePublic();
    }


    /**
     * Checks if the badge is visible otherwise it makes the badge visible and initiates
     * a notification.
     */
    public void checkBadgeBike() {
        if (badgeBike) {
            return;
        } else if (bikeDistance >= 100) {
            System.out.println("jaja");
            new BadgeModel().updateBadge("distanceByBikeBadge");
            try {
                new WindowsNotifications().notification(
                    "3.png",
                    "Congrats! You have cycled 100 km!");
            } catch (AWTException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


        }
    }

    /**
     * Checks if the badge is visible otherwise it makes the badge visible and initiates
     * a notification.
     */
    public void checkBadgeNoCar() {
        if (badgeNoCar) {
            return;
        } else if (bikeDistance + publicDistance >= 500) {
            new BadgeModel().updateBadge("kmNoCarUsedBadge");
            try {
                new WindowsNotifications().notification(
                    "2.png",
                    "Congrats! You did not use your car for already 500km!");
            } catch (AWTException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Checks if the badge is visible otherwise it makes the badge visible and initiates
     * a notification.
     */
    public void checkBadgePublic() {
        if (badgePublic) {
            return;
        } else if (publicDistance >= 600) {

            new BadgeModel().updateBadge("distancePublicBadge");
            try {
                new WindowsNotifications().notification(
                    "7.png",
                    "Congrats! You did use public transportation for already 600km!");
            } catch (AWTException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }
}




