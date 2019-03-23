package model;

import model.objects.UserProfile;
import model.objects.VegetarianRequest;
import model.objects.VegetarianResponse;
import org.springframework.web.client.RestTemplate;

public class FeaturesModel {

    private int vegMealCounter;

    public FeaturesModel() {
        vegMealCounter = 0;
    }

    /**
     * Increases the User's reduced CO2 and score based on the amount of vegetarian meals eaten.
     * @param amount - The amount of meals eaten
     */
    public void vegMeal(int amount) {
        final String uri = "http://localhost:8080/vegmeal";

        UserProfile.getInstance().increaseExp(5 * amount);
        UserProfile.getInstance().reduceCo2(3.0 * amount);
        vegMealCounter += amount;

        VegetarianRequest vegetarianRequest = new VegetarianRequest(UserProfile.getInstance().getUid(), amount);

        RestTemplate restTemplate = new RestTemplate();

        VegetarianResponse result = restTemplate.postForObject(uri, vegetarianRequest, VegetarianResponse.class);
        System.out.println(result.getExpIncrease());
    }

    public int getVegMealCounter() {
        return vegMealCounter;
    }

}
