package model;

import model.objects.PaperRecyclingRequest;
import model.objects.PaperRecyclingResponse;
import org.springframework.web.client.RestTemplate;
import supporting.ServerApi;

public class RecyclingModel {

    private float paperRecyclingCount = -1;

    /**
     * Initializes the PaperRecycling feature of the user profile.
     */
    public void init() {

        if (UserProfile.getInstance().authToken.isEmpty()) {
            paperRecyclingCount = 0;
            return;
        }

        addAmountPaperRecycling(0);
    }

    /**
     * Returns the total amount of recycled paper.
     * @return - the amount of recycled paper
     */
    public float getPaperRecyclingCount() {

        if (paperRecyclingCount < 0) {
            this.init();
        }

        return paperRecyclingCount;
    }

    /**
     * Invokes request to server, notifying it about the action of amount of recycled paper.
     * @param kg - weight of amount in kg
     */
    public void addAmountPaperRecycling(float kg) {

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
}
