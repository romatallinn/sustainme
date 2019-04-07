package model;

import model.objects.FractalTreeRequest;
import model.objects.FractalTreeResponse;
import org.springframework.web.client.RestTemplate;
import supporting.ServerApi;

public class FractalTreeModel {

    /**
     * Gets the co2 reduced data out of the database.
     * @return result - returns all the co2 reduced data per feature
     */
    public FractalTreeResponse fractalTreeGetData() {

        final String uri = ServerApi.HOST + ServerApi.LOCAL_PRODUCE_EATEN;

        FractalTreeRequest fractalTreeRequest =
            new FractalTreeRequest(UserProfile.getInstance().getUid());

        RestTemplate restTemplate = new RestTemplate();
        FractalTreeResponse result =
            restTemplate.postForObject(uri, fractalTreeRequest, FractalTreeResponse.class);

        return result;
    }
}
