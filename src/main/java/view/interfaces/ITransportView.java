package view.interfaces;

import controller.TransportController;

public interface ITransportView {

    void initView(TransportController controller);

    void displayStatusBike(String msg);

    void displayStatusPublic(String msg);

    void updateBikeDistance(int distance);

    void updatePublicDistance(int distance);

    void clearInputFields();
}
