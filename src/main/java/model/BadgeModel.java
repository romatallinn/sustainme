package model;

import model.objects.BadgeRequest;
import org.springframework.web.client.RestTemplate;
import supporting.ServerApi;

public class BadgeModel {

    /**
     * Receives if the badge is set on true in the database.
     * @param badges    - badges
     * @return result   - true or false
     */
    public Boolean receiveBadge(String badges) {

        final String uri = ServerApi.HOST + ServerApi.BADGES;

        BadgeRequest badgeRequest =
            new BadgeRequest(UserProfile.getInstance().getUid(), badges );

        RestTemplate restTemplate = new RestTemplate();
        Boolean result =
            restTemplate.postForObject(uri, badgeRequest, Boolean.class);

        return result;

    }

    /**
     * Updates the badge in the database to true.
     * @param badges - badges
     */
    public void updateBadge(String badges) {

        final String uri = ServerApi.HOST + ServerApi.UPDATE_BADGES;

        BadgeRequest badgeRequest =
            new BadgeRequest(UserProfile.getInstance().getUid(), badges);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, badgeRequest, Boolean.class);
    }
}
