package model;

import model.objects.BikeRequest;
import model.objects.BikeResponse;
import model.objects.UserProfile;
import model.objects.VegetarianRequest;
import model.objects.VegetarianResponse;
import org.springframework.web.client.RestTemplate;
import supporting.ServerApi;

public class FeaturesModel {

    /**
     * Increases the User's reduced CO2 and score based on the amount of vegetarian meals eaten.
     * @param amount - The amount of meals eaten
     */
    public void vegMeal(int amount) {

        final String uri = ServerApi.HOST + ServerApi.VEGMEAL_EATEN;

        VegetarianRequest vegetarianRequest =
                new VegetarianRequest(UserProfile.getInstance().getUid(), amount);

        RestTemplate restTemplate = new RestTemplate();
        VegetarianResponse result =
                restTemplate.postForObject(uri, vegetarianRequest, VegetarianResponse.class);

        UserProfile.getInstance().setLocalExp(result.getExperience());
        UserProfile.getInstance().setLocalCo2Stats(result.getCo2Reduced());

        UserProfile.getInstance().setLocalVegMealsCounter(result.getAmount());
    }

    /**
     * Increases the User's reduced CO2 and score based on the distance cycled.
     * @param distance distance cycled
     */
    public void bike(int distance) {

        final String uri = ServerApi.HOST + ServerApi.BICYCLE;

        BikeRequest bikeRequest =
                new BikeRequest(UserProfile.getInstance().getUid(), distance);

        RestTemplate restTemplate = new RestTemplate();
        BikeResponse result =
                restTemplate.postForObject(uri, bikeRequest, BikeResponse.class);

        UserProfile.getInstance().setLocalExp(result.getExperience());
        UserProfile.getInstance().setLocalCo2Stats(result.getCo2Reduced());

        UserProfile.getInstance().setLocalBikeDistance(result.getDistance());
    }



}
