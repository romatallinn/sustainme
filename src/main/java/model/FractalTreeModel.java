package model;


import supporting.ServerApi;
import org.springframework.web.client.RestTemplate;

import model.objects.FractalTreeRequest;
import model.objects.FractalTreeResponse;

public class FractalTreeModel {

    public void FractalTreeGetData() {

        final String uri = ServerApi.HOST + ServerApi.LOCAL_PRODUCE_EATEN;

        FractalTreeRequest fractalTreeRequest =
            new FractalTreeRequest(UserProfile.getInstance().getUid());

        RestTemplate restTemplate = new RestTemplate();
        FractalTreeResponse result =
            restTemplate.postForObject(uri, fractalTreeRequest, FractalTreeResponse.class);
    }
}
