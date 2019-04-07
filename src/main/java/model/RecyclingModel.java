package model;

import model.objects.PaperRecyclingRequest;
import model.objects.PaperRecyclingResponse;
import model.objects.PlasticRecyclingRequest;
import model.objects.PlasticRecyclingResponse;
import org.springframework.web.client.RestTemplate;
import supporting.ServerApi;

public class RecyclingModel {

    private double paperRecyclingCount = -1;
    private double plasticRecyclingCount = -1;

    /**
     * Initializes the Recycling feature of the user profile.
     */
    public void init() {

        if (UserProfile.getInstance().authToken.isEmpty()) {
            paperRecyclingCount = 0;
            plasticRecyclingCount = 0;
            return;
        }

        addAmountPaperRecycling(0);
        addAmountPlasticRecycling(0);
    }

    /**
     * Returns the total amount of recycled paper.
     * @return - the amount of recycled paper
     */
    public double getPaperRecyclingCount() {

        if (paperRecyclingCount < 0) {
            this.init();
        }

        return paperRecyclingCount;
    }

    /**
     * Invokes request to server, notifying it about the action of amount of recycled paper.
     * @param kg - weight of amount in kg
     */
    public void addAmountPaperRecycling(double kg) {

        if (UserProfile.getInstance().authToken.isEmpty()) {
            paperRecyclingCount = 0;
            return;
        }

        final String uri = ServerApi.HOST + ServerApi.PAPER_RECYCLING;

        PaperRecyclingRequest paperRecyclingRequest =
            new PaperRecyclingRequest(UserProfile.getInstance().getUid(), kg);

        RestTemplate restTemplate = new RestTemplate();
        PaperRecyclingResponse result =
            restTemplate.postForObject(uri, paperRecyclingRequest, PaperRecyclingResponse.class);

        UserProfile.getInstance().setLocalExp(result.getExperience());
        UserProfile.getInstance().setLocalCo2Stats(result.getCo2Reduced());

        paperRecyclingCount = result.getAmount();
    }

    /**
     * Returns the total amount of recycled plastic.
     * @return - the amount of recycled plastic
     */
    public double getPlasticRecyclingCount() {

        if (plasticRecyclingCount < 0) {
            this.init();
        }

        return plasticRecyclingCount;
    }

    /**
     * Invokes request to server, notifying it about the action of amount of recycled paper.
     * @param kg - weight of amount in kg
     */
    public void addAmountPlasticRecycling(double kg) {

        if (UserProfile.getInstance().authToken.isEmpty()) {
            plasticRecyclingCount = 0;
            return;
        }

        final String uri = ServerApi.HOST + ServerApi.PLASTIC_RECYCLING;

        PlasticRecyclingRequest plasticRecyclingRequest =
                new PlasticRecyclingRequest(UserProfile.getInstance().getUid(), kg);

        RestTemplate restTemplate = new RestTemplate();
        PlasticRecyclingResponse result =
                restTemplate.postForObject(
                    uri, plasticRecyclingRequest, PlasticRecyclingResponse.class);

        UserProfile.getInstance().setLocalExp(result.getExperience());
        UserProfile.getInstance().setLocalCo2Stats(result.getCo2Reduced());

        plasticRecyclingCount = result.getAmount();
    }
}
