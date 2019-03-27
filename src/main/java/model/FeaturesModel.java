package model;

import model.objects.UserProfile;
import model.objects.VegetarianRequest;
import model.objects.VegetarianResponse;
import org.springframework.web.client.RestTemplate;
import supporting.ServerApi;

public class FeaturesModel {

    private int vegMealCounter;
    private int bikeDistance;

    public FeaturesModel() {
        vegMealCounter = 0;
    }

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

    public int getVegMealCounter() {
        return vegMealCounter;
    }

}
