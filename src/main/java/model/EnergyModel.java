package model;

import model.objects.SolarRequest;
import model.objects.SolarResponse;
import model.objects.TemperatureRequest;
import org.springframework.web.client.RestTemplate;
import supporting.ServerApi;

public class EnergyModel {

    private int solarArea = -1;
    private double homeTemperature = -1;


    /**
     * Initializes the energy features of the user profile.
     */
    public void init() {

        if (UserProfile.getInstance().authToken.isEmpty()) {
            solarArea = 0;
            homeTemperature = 0;
            return;
        }

        addSolarArea(0);
        lowerTemperature(21);
    }

    /**
     * Returns the total area of solar panels owned.
     * @return the total area covered by the solar panels.
     */
    public int getSolarArea() {
        if (solarArea < 0) {
            this.init();
        }

        return solarArea;
    }

    /**
     * Returns the current temperature in the house.
     * @return the current temperature.
     */
    public double getHomeTemperature() {
        if (homeTemperature < 0) {
            this.init();
        }

        return homeTemperature;
    }

    /**
     * Invokes request to server, notifying it about the action of adding solar panels.
     * @param area - The area of solar panels added
     */
    public void addSolarArea(int area) {

        if (UserProfile.getInstance().authToken.isEmpty()) {
            solarArea = 0;
            return;
        }

        final String uri = ServerApi.HOST + ServerApi.SOLAR_PANEL;

        SolarRequest solarRequest = new SolarRequest(UserProfile.getInstance().getUid(), area);

        RestTemplate restTemplate = new RestTemplate();
        SolarResponse result = restTemplate.postForObject(uri, solarRequest, SolarResponse.class);

        UserProfile.getInstance().setLocalExp(result.getExperience());

        solarArea = result.getArea();
    }

    /**
     * Invokes request to server, notifying it about the action of updating the temperature.
     * @param temp - the new temperature
     */
    public void lowerTemperature(double temp) {

        if (UserProfile.getInstance().authToken.isEmpty()) {
            homeTemperature = 21;
            return;
        }

        final String uri = ServerApi.HOST + ServerApi.HOUSE_TEMPERATURE;

        TemperatureRequest tempRequest =
                new TemperatureRequest(UserProfile.getInstance().getUid(), temp);

        RestTemplate restTemplate = new RestTemplate();
        double result = restTemplate.postForObject(uri, tempRequest, Double.class);

        homeTemperature = result;
    }
}
