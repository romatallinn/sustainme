package model;

import model.objects.LocalProduceRequest;
import model.objects.LocalProduceResponse;
import model.objects.VegetarianRequest;
import model.objects.VegetarianResponse;
import org.springframework.web.client.RestTemplate;
import supporting.ServerApi;

public class FoodModel {

    private int vegMealsCount = -1;
    private double localProduceCount = -1;


    /**
     * Initializes the food features of the user profile.
     */
    public void init() {

        if (UserProfile.getInstance().authToken.isEmpty()) {
            vegMealsCount = 0;
            localProduceCount = 0;
            return;
        }

        addEatenVegMeal(0);
        addEatenLocalProduce(0);

    }

    /**
     * Returns the total amount of vegetarian meals eaten by the user.
     * @return the amount of vegetarian meals
     */
    public int getVegMealsCount() {

        if (vegMealsCount < 0) {
            this.init();
        }

        return vegMealsCount;
    }

    /**
     * Returns the total amount of local produce bought.
     * @return the amount of local produce bought.
     */
    public double getLocalProduceCount() {

        if (localProduceCount < 0) {
            this.init();
        }

        return localProduceCount;

    }


    /**
     * Invokes request to server, notifying it about the action of eaten vegetarian meal.
     * @param amount - The amount of meals eaten
     */
    public void addEatenVegMeal(int amount) {

        if (UserProfile.getInstance().authToken.isEmpty()) {
            vegMealsCount = 0;
            return;
        }

        final String uri = ServerApi.HOST + ServerApi.VEGMEAL_EATEN;

        VegetarianRequest vegetarianRequest =
                new VegetarianRequest(UserProfile.getInstance().getUid(), amount);

        RestTemplate restTemplate = new RestTemplate();
        VegetarianResponse result =
                restTemplate.postForObject(uri, vegetarianRequest, VegetarianResponse.class);

        UserProfile.getInstance().setLocalExp(result.getExperience());
        UserProfile.getInstance().setLocalCo2Stats(result.getCo2Reduced());

        vegMealsCount = result.getAmount();

    }

    /**
     * Invokes request to server, notifying it about the action of eaten local produce.
     * @param kg - weight of the eaten local produce in kg.
     */
    public void addEatenLocalProduce(float kg) {

        if (UserProfile.getInstance().authToken.isEmpty()) {
            localProduceCount = 0;
            return;
        }

        final String uri = ServerApi.HOST + ServerApi.LOCAL_PRODUCE_EATEN;

        LocalProduceRequest localProduceRequest =
                new LocalProduceRequest(UserProfile.getInstance().getUid(), kg);

        RestTemplate restTemplate = new RestTemplate();
        LocalProduceResponse result =
                restTemplate.postForObject(uri, localProduceRequest, LocalProduceResponse.class);

        UserProfile.getInstance().setLocalExp(result.getExperience());
        UserProfile.getInstance().setLocalCo2Stats(result.getCo2Reduced());

        localProduceCount = result.getAmount();

    }


}
