package model;

import model.objects.BadgeRequest;
import model.objects.LocalProduceRequest;
import model.objects.LocalProduceResponse;
import org.springframework.web.client.RestTemplate;
import supporting.ServerApi;

public class BadgeModel {

    public Boolean receiveBadge(String badges) {

        final String uri = ServerApi.HOST + ServerApi.BADGES;

        BadgeRequest badgeRequest =
            new BadgeRequest(UserProfile.getInstance().getUid(), badges );

        RestTemplate restTemplate = new RestTemplate();
        Boolean result =
            restTemplate.postForObject(uri, badgeRequest, Boolean.class);

        return result;

    }

    public void updateBadge(String badges) {

        final String uri = ServerApi.HOST + ServerApi.UPDATE_BADGES;

        BadgeRequest badgeRequest =
            new BadgeRequest(UserProfile.getInstance().getUid(), badges);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, badgeRequest, Boolean.class);
    }
}
