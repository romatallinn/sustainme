package controller;

import model.TransportModel;
import model.UserProfile;
import view.interfaces.ITransportView;

public class TransportController {

    private ITransportView view;
    private TransportModel model;

    /**
     * Constructor for the class.
     * @param view that is represented by the controller.
     */
    public TransportController(ITransportView view) {
        this.view = view;
        this.model = new TransportModel();
    }

    /**
     * Retrieves amount of kilometers cycled and sends this to the view to update.
     */
    public void updateViewWithData() {
        int distance = model.getBikeDistance();
        view.updateBikeDistance(distance);
    }

    /**
     * The method acts upon the fact of the cycled kilometers.
     * @param distanceString of the kilometers cycled.
     */
    public void addBikeKms(String distanceString) {
        int distance = 0;
        try {
            distance = Integer.parseInt(distanceString);
            if (distance < 0 || distance > 200) {
                view.displayStatus("Please enter a number in the range 0-200.");
                return;
            }
            model.addDistanceCycled(distance);
            view.displayStatus("The stat of the cycled distance is updated!");
            updateViewWithData();
        } catch (NumberFormatException e) {
            view.displayStatus("Please enter an integer number.");
        }
    }
}
