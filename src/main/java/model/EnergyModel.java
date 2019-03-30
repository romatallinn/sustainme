package model;

import model.objects.UserProfile;

public class EnergyModel {

    private int solarPanelArea = -1;

    public int getSolarPanelArea() {

        if (solarPanelArea < 0) {
            init();
        }

        return solarPanelArea;
    }

    private void init() {

        if (UserProfile.getInstance().authToken.isEmpty()) {
            solarPanelArea = 0;
            return;
        }

        addAreaSolarPanels(0);

    }

    public void addAreaSolarPanels(int area) {
        // TODO
    }



}
